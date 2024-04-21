package fr.pay.scim.server.endpoint;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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

import fr.pay.scim.server.endpoint.entity.ScimResources;
import fr.pay.scim.server.endpoint.entity.error.ScimError;
import fr.pay.scim.server.endpoint.entity.user.ScimUser;
import fr.pay.scim.server.endpoint.exception.ScimConflictException;
import fr.pay.scim.server.endpoint.exception.ScimException;
import fr.pay.scim.server.endpoint.exception.ScimInternalServerErrorException;
import fr.pay.scim.server.endpoint.exception.ScimNotFoundException;
import fr.pay.scim.server.endpoint.mapper.ScimToUserMapper;
import fr.pay.scim.server.endpoint.mapper.UserToScimMapper;
import fr.pay.scim.server.service.UserService;
import fr.pay.scim.server.service.entity.user.User;
import fr.pay.scim.server.service.entity.user.Users;
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
@RequestMapping("/scim/v2/Users")
public class ScimUserEndPoint {

	@Autowired
	private UserService userService;

	@Autowired
	private UserToScimMapper userToScimMapper;

	@Autowired
	private ScimToUserMapper scimToUserMapper;

    // ========================================================
	// = Mapper
	// ========================================================
	
	private ScimUser mapper(User user, String location) {
		return userToScimMapper.mapper(user, location);
	}
	
	private User mapper(ScimUser scimUser) {
		return scimToUserMapper.mapper(null, scimUser);
	}

	private User mapper(User user, ScimUser scimUser) {
		return scimToUserMapper.mapper(user, scimUser);
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
	
	@Operation(summary = "Creating of a user")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "The user is created.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimUser.class))}),
			@ApiResponse(responseCode = "409", description = "User already exists.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))})
			})
	@PostMapping("")
	public ResponseEntity<ScimUser> create(
			@RequestBody @Validated ScimUser scimUser,
			HttpServletRequest request
			) throws ScimException {

		try {
			log.info("Demande de création de compte : {}", scimUser);

			// On contrôle de l'username n'existe pas
			if (userService.findUserByUserName(scimUser.getUserName()) != null) {
				throw new ScimConflictException("UserName already exists.");
			}

			// Conversion de l'object scimUser en user
			User user = mapper(scimUser);

			// Création de l'utilisateur
			user = userService.createUser(user);
			
			// Recherche de l'url de la resource
			String location = location(request, user.getId());

			// Conversion de l'object user en scimUser
			scimUser = mapper(user, location);
			log.info("Demande de création de compte effectuée: {}", scimUser);

			// Generation du message de réponse
			return ResponseEntity
						.status(HttpStatus.CREATED)
						.header("Content-Type", "application/scim+json")
						.header("Location", location)
						.body(scimUser);


		} catch (URISyntaxException e) {
			log.error("Demande de création de compte : {} -> {}", scimUser, e.getMessage());
			
			throw new ScimInternalServerErrorException("Internal Error");
		}
	}
	
	
	// ----------------------------------------------------------------------------
	// - GET : "/{id}"
	// ----------------------------------------------------------------------------

	@Operation(summary = "Search for a user")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "The user is found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimUser.class))}),
			@ApiResponse(responseCode = "404", description = "User not found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))})
	})
	@GetMapping("/{id}")
	public ResponseEntity<ScimUser> read(
			@Parameter(description = "Id of user to be searched.") @PathVariable String id,
			HttpServletRequest request
			) throws ScimException {
		
		try {
			log.info("Recherche d'un compte : {}", id);
			
			// Recherche de l'utilisateur
			User user = userService.findUser(id);

			// Si l'utilisateur n'existe pas alors erreur 404
			if (user == null) {
				throw new ScimNotFoundException("User not found.");			
			}

			// Recherche de l'url de la resource
			String location = location(request);

			// Conversion de l'objet user en scimUser
			ScimUser scimUser = mapper(user, location);
			log.info("Recherche d'un compte effectuée : {}", scimUser);
							
			// Generation de la réponse
			return ResponseEntity
					.status(HttpStatus.OK)
					.header("Content-Type", "application/scim+json")
					.header("Location", location)
					.body(scimUser);

		} catch (URISyntaxException e) {
			log.error("Recherche d'un compte : {} -> {}", id, e.getMessage());
			
			throw new ScimInternalServerErrorException("Internal Error");
		}
	}

	
	// ----------------------------------------------------------------------------
	// - PUT : "/{id}"
	// ----------------------------------------------------------------------------

	
	@Operation(summary = "Replacing a user")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "The user's has been updated.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimUser.class))}),
			@ApiResponse(responseCode = "404", description = "User not found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))})
			})
	@PutMapping("/{id}")
	public ResponseEntity<ScimUser> replace(
			@Parameter(description = "Id of user to be searched.") @PathVariable String id,
			@RequestBody @Validated ScimUser scimUser,
			HttpServletRequest request
			) throws ScimException {

		try {

			log.info("Demande de modification de compte : {}", scimUser);
				
			// Recherche de l'utilisateur
			User user = userService.findUser(id);

			// Si l'utilisateur n'existe pas alors erreur 404
			if (user == null) {
				throw new ScimNotFoundException("User not found.");			
			}

			// Conversion du scimUser en user (avec les anciennes valeurs comme ref)
			user = mapper(user, scimUser);

			// c'est l'id de la request qui fait reference.
			user.setId(id);

			// On fait la mise à jour
			user = userService.updateUser(user);

			// Recherche de l'url de la resource
			String location = location(request);

			// Conversion de l'objet user en scimUser
			scimUser = mapper(user, location);
			log.info("Demande de modification de compte effectuée : {}", scimUser);
			
			return ResponseEntity
					.status(HttpStatus.OK)    
					.header("Content-Type", "application/scim+json")
					.header("Location", location)
					.body(scimUser);

		} catch (URISyntaxException e) {
			log.error("Demande de modification de compte : {} -> {}", scimUser, e.getMessage());
				
			throw new ScimInternalServerErrorException("Internal Error");
		}
	}
	
	
	// ----------------------------------------------------------------------------
	// - DELETE : "/{id}"
	// ----------------------------------------------------------------------------

	@Operation(summary = "Deleting a user")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "204", description = "User deleted.", content = { @Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404", description = "User not found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))})
			})
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(
			@Parameter(description = "User ID") @PathVariable String id
			) throws ScimException {
		
		log.info("Demande de suppression de compte : {}", id);
		
		// Recherche de l'utilisateur
		User user = userService.findUser(id);

		// Si l'utilisateur n'existe pas alors erreur 404
		if (user == null) {
			throw new ScimNotFoundException("User not found.");			
		}

		userService.delete(id);
		
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
	
	@Operation(summary = "Search for users")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "users trouvés", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimResources.class))})
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
		
		log.info("Recherche d'une liste de comptes");		

		Users users = userService.findUsers(filter, attributes, excludedAttributes, sortBy, sortOrder, startIndex, count);

		List<ScimUser> resources = new ArrayList<>();

		for (User user : users.getUsers()) {
			resources.add(mapper(user, location(request, user.getId())));
		}
		
		ScimResources scimUsers = new ScimResources();
		scimUsers.setTotalResults(users.getTotalResults());
		scimUsers.setItemsPerPage(count);
		scimUsers.setStartIndex(startIndex);
		scimUsers.setResources(resources);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Content-Type", "application/scim+json;charset=UTF-8")
//				.header("Location", location)
				.body(scimUsers);
	}



}
