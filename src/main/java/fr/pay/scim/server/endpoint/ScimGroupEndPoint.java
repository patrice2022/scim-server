package fr.pay.scim.server.endpoint;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.ForwardedHeaderUtils;

import fr.pay.scim.server.endpoint.entity.ScimMeta;
import fr.pay.scim.server.endpoint.entity.ScimResources;
import fr.pay.scim.server.endpoint.entity.error.ScimError;
import fr.pay.scim.server.endpoint.entity.group.ScimGroup;
import fr.pay.scim.server.endpoint.entity.group.ScimMember;
import fr.pay.scim.server.endpoint.exception.ScimConflictException;
import fr.pay.scim.server.endpoint.exception.ScimException;
import fr.pay.scim.server.endpoint.exception.ScimNotFoundException;
import fr.pay.scim.server.service.GroupService;
import fr.pay.scim.server.service.entity.group.Group;
import fr.pay.scim.server.service.entity.group.Groups;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/scim/v2/Groups")
public class ScimGroupEndPoint {

	@Autowired
	private GroupService groupService;

    // ========================================================
	// = Mapper
	// ========================================================

	private ScimGroup mapper(Group group, String location) {

		ScimGroup scimGroup = new ScimGroup();
		
		scimGroup.setSchemas(Arrays.asList("urn:ietf:params:scim:schemas:core:2.0:Group"));		// READ_WRITE

		ScimMeta scimMeta = new ScimMeta();
		scimMeta.setResourceType("Group");
		scimMeta.setLocation(location);
		scimMeta.setCreated(group.getCreated());
		scimMeta.setLastModified(group.getLastModified());
		scimGroup.setMeta(scimMeta);																// READ_ONLY

		scimGroup.setId(group.getId());															// READ_ONLY
		scimGroup.setExternalId(group.getExternalId());											// READ_WRITE
		scimGroup.setDisplayName(group.getDisplayName());										// READ_WRITE

		if (group.getMembers() != null && !group.getMembers().isEmpty()) {
				
			List<ScimMember> scimMembers = new ArrayList<>();
			
			for (String membre : group.getMembers()) {
				
				ScimMember scimMember = new ScimMember();
				scimMember.setValue(membre);
//				scimMember.setDisplay(user.getUserName());
				scimMembers.add(scimMember);
			}
		
			
			scimGroup.setMembers(scimMembers);
		}


		return scimGroup;
	}

	private Group mapper(ScimGroup scimGroup) {
		
		// id			READ_ONLY

		Group group = new Group()
				.setExternalId(scimGroup.getExternalId())					// READ_WRITE
				.setDisplayName(scimGroup.getDisplayName());				// READ_WRITE

		List<String> members = new ArrayList<>();
		if (scimGroup.getMembers() != null) {
					
			for (ScimMember scimMember : scimGroup.getMembers()) {
				members.add(scimMember.getValue());
			}

		}
		group.setMembers(members);					// READ_WRITE
	
		return group;
	}

	
	private Group mapper(Group group, ScimGroup scimGroup) {

		// id			READ_ONLY

		group.setExternalId(scimGroup.getExternalId());						// READ_WRITE
		group.setDisplayName(scimGroup.getDisplayName());					// READ_WRITE

		List<String> members = new ArrayList<>();
		if (scimGroup.getMembers() != null) {
					
			for (ScimMember scimMember : scimGroup.getMembers()) {
				members.add(scimMember.getValue());
			}

		}
		group.setMembers(members);					// READ_WRITE

		return group;

	}


	// ========================================================
	// = location
	// ========================================================

	private String location(HttpServletRequest request, String id) throws URISyntaxException {
		
		URI url = new URI(request.getRequestURL().toString());
		
		ServletServerHttpRequest sshr = new ServletServerHttpRequest(request);

		String location = ForwardedHeaderUtils.adaptFromForwardedHeaders(url, sshr.getHeaders())
				.path("/" + id)
				.build()
				.toUriString();
		
		return location;
	}

	private String location(HttpServletRequest request) throws URISyntaxException {
		
		URI url = new URI(request.getRequestURL().toString());
		
		ServletServerHttpRequest sshr = new ServletServerHttpRequest(request);

		String location = ForwardedHeaderUtils.adaptFromForwardedHeaders(url, sshr.getHeaders())
				.build()
				.toUriString();
		
		return location;
	}	

		




    // ========================================================
	// = CRUD
	// ========================================================

	// ----------------------------------------------------------------------------
	// - POST : ""
	// ----------------------------------------------------------------------------
	
	@Operation(summary = "Creating a group")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "The group is created.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimGroup.class))}),
			@ApiResponse(responseCode = "409", description = "Group already exists.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))})
			})
	@PostMapping("")
	public ResponseEntity<ScimGroup> create(
			@RequestBody @Validated ScimGroup scimGroup,
			HttpServletRequest request
			) throws ScimException, URISyntaxException {

		log.info("Demande de création d'un groupe : {}", scimGroup);

		// On contrôle de le groupe n'existe pas
		if (groupService.findGroupByDisplayName(scimGroup.getDisplayName()) != null) {
			throw new ScimConflictException("UserName already exists.");
		}

		// Conversion de l'object scimGroup en group
		Group group = mapper(scimGroup);
        
		// Création de l'utilisateur
		group = groupService.createGroup(group);
		
		// Recherche de l'url de la resource
		String location = location(request, group.getId());

		// Conversion de l'object user en scimUser
		scimGroup = mapper(group, location);
		log.info("Demande de création du groupe effectuée: {}", scimGroup);

		// Generation du message de réponse
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.header("Content-Type", "application/scim+json")
				.header("Location", location)
				.body(scimGroup);
	}

 	
	// ----------------------------------------------------------------------------
	// - GET : "/{id}"
	// ----------------------------------------------------------------------------

	@Operation(summary = "Search for a group")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "The group is found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimGroup.class))}),
			@ApiResponse(responseCode = "404", description = "Group not found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))})
	})
	@GetMapping("/{id}")
	public ResponseEntity<ScimGroup> read(
			@Parameter(description = "Id of group to be searched.") @PathVariable String id,
			HttpServletRequest request
			) throws ScimException, URISyntaxException {
		
		log.info("Recherche d'un groupe : {}", id);
					
		// Recherche de l'utilisateur
		Group group = groupService.findGroup(id);

		// Si le groupe n'existe pas alors erreur 404
		if (group == null) {
			throw new ScimNotFoundException("Group not found.");			
		}

		// Recherche de l'url de la resource
		String location = location(request);		

		// Conversion de l'objet group en scimGroup
		ScimGroup scimGroup = mapper(group, location);
		log.info("Recherche d'un group effectuée : {}", scimGroup);
					
		// Generation de la réponse
		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Content-Type", "application/scim+json")
				.header("Location", location)
				.body(scimGroup);
	}


	// ----------------------------------------------------------------------------
	// - PUT : "/{id}"
	// ----------------------------------------------------------------------------
	
	@Operation(summary = "Replacing a group")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "The group's has been updated.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimGroup.class))}),
			@ApiResponse(responseCode = "404", description = "Group not found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))})
			})
	@PutMapping("/{id}")
	public ResponseEntity<ScimGroup> replace(
			@Parameter(description = "Id of group to be searched.") @PathVariable String id,
			@RequestBody @Validated ScimGroup scimGroup,
			HttpServletRequest request
			) throws ScimException, URISyntaxException {

		log.info("Demande de modification du groupe : {}", scimGroup);

		// Recherche de l'utilisateur
		Group group = groupService.findGroup(id);
		
		// Si l'utilisateur n'existe pas alors erreur 404
		if (group == null) {
			throw new ScimNotFoundException("Group not found.");			
		}

		// Conversion du scimUser en user (avec les anciennes valeurs comme ref)
		group = mapper(group, scimGroup);

		// c'est l'id de la request qui fait reference.
		group.setId(id);

		// On fait la mise à jour
		group = groupService.updateGroup(group);

		// Recherche de l'url de la resource
		String location = location(request);

		// Conversion de l'objet user en scimUser
		scimGroup = mapper(group, location);

		log.info("Demande de modification du groupe effectuée : {}", scimGroup);

		return ResponseEntity
					.status(HttpStatus.OK)    
					.header("Content-Type", "application/scim+json")
					.header("Location", location)
					.body(scimGroup);
	}


	// ----------------------------------------------------------------------------
	// - DELETE : "/{id}"
	// ----------------------------------------------------------------------------

	@Operation(summary = "Deleting a group")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "204", description = "Group deleted.", content = { @Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404", description = "Group not found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))})
			})
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(
			@Parameter(description = "User ID") @PathVariable String id
			) throws ScimException {
		
		log.info("Demande de suppression de groupe : {}", id);
		
		// Recherche de l'utilisateur
		Group group = groupService.findGroup(id);

		// Si le groupe n'existe pas alors erreur 404
		if (group == null) {
			throw new ScimNotFoundException("Group not found.");			
		}

		groupService.delete(id);
		
		return ResponseEntity
				.noContent()
				.build();
    }


    // ========================================================
	// = Search
	// ========================================================

	// ----------------------------------------------------------------------------
	// - GET : ""
	// ----------------------------------------------------------------------------
	
	@Operation(summary = "Search for groups")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "groupes trouvés", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimResources.class))})
			})
	@GetMapping("")
	public ResponseEntity<ScimResources> findUsers(
			@Parameter(description = "filter") @RequestParam(required = false) String filter,
			@Parameter(description = "attributes") @RequestParam(required = false) String attributes,
			@Parameter(description = "excludedAttributes") @RequestParam(required = false) String excludedAttributes,
			@Parameter(description = "sortBy") @RequestParam(required = false) String sortBy,
			@Parameter(description = "sortOrder") @RequestParam(required = false) String sortOrder,
			@Parameter(description = "startIndex") @RequestParam(defaultValue = "1", required = false) int startIndex,
			@Parameter(description = "count") @RequestParam(defaultValue = "10", required = false) int count,
			HttpServletRequest request
			) throws ScimException, URISyntaxException {
		
		log.info("Recherche d'une liste de groupes");		

		Groups groups = groupService.findGroups(filter, attributes, excludedAttributes, sortBy, sortOrder, startIndex, count);

		List<ScimGroup> resources = new ArrayList<>();

		for (Group group : groups.getGroups()) {
			resources.add(mapper(group, location(request, group.getId())));
			
		}

		ScimResources scimGroups = new ScimResources();
		scimGroups.setTotalResults(groups.getTotalResults());
		scimGroups.setItemsPerPage(count);
		scimGroups.setStartIndex(startIndex);
		scimGroups.setResources(resources);

		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Content-Type", "application/scim+json;charset=UTF-8")
//				.header("Location", location)
				.body(scimGroups);
	}

}
