package fr.pay.scim.server.endpoint;

import org.springframework.http.ResponseEntity;
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

import fr.pay.scim.server.endpoint.entity.ScimResources;
import fr.pay.scim.server.endpoint.entity.error.ScimError;
import fr.pay.scim.server.endpoint.entity.group.ScimGroup;
import fr.pay.scim.server.endpoint.exception.ScimException;
import fr.pay.scim.server.endpoint.exception.ScimNotImplementedException;
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
			) throws ScimException {

		log.info("Demande de création d'un groupe : {}", scimGroup);

        throw new ScimNotImplementedException("En attente d'implémentation");
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
			) throws ScimException {
		
		log.info("Recherche d'un groupe : {}", id);
		
        throw new ScimNotImplementedException("En attente d'implémentation");
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
			) throws ScimException {

		log.info("Demande de modification de compte : {}", scimGroup);
			
		throw new ScimNotImplementedException("En attente d'implémentation");
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
		
		throw new ScimNotImplementedException("En attente d'implémentation");
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
			) throws ScimException {
		
		log.info("Recherche d'une liste de groupes");		

		throw new ScimNotImplementedException("En attente d'implémentation");
	}

}
