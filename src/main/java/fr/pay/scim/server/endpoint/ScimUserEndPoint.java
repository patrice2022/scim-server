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
import fr.pay.scim.server.endpoint.entity.user.ScimUser;
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
@RequestMapping("/scim/v2/Users")
public class ScimUserEndPoint {

    // ========================================================
	// = CRUD
	// ========================================================

	// ----------------------------------------------------------------------------
	// - POST : ""
	// ----------------------------------------------------------------------------
	
	@Operation(summary = "Creation of a user.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "The user is created.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimUser.class))}),
			@ApiResponse(responseCode = "409", description = "User already exists.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))})
			})
	@PostMapping("")
	public ResponseEntity<ScimUser> create(
			@RequestBody @Validated ScimUser scimUser,
			HttpServletRequest request
			) throws ScimException {

		log.info("Demande de création de compte : {}", scimUser);

        throw new ScimNotImplementedException("En attente d'implémentation");
	}
	
	
	// ----------------------------------------------------------------------------
	// - GET : "/{id}"
	// ----------------------------------------------------------------------------

	
	@Operation(summary = "search for a user")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "The user is found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimUser.class))}),
			@ApiResponse(responseCode = "404", description = "User not found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))})
	})
	@GetMapping("/{id}")
	public ResponseEntity<ScimUser> read(
			@Parameter(description = "Id of user to be searched.") @PathVariable String id,
			HttpServletRequest request
			) throws ScimException {
		
		log.info("Recherche d'un compte : {}", id);
		
        throw new ScimNotImplementedException("En attente d'implémentation");
	}

	
	// ----------------------------------------------------------------------------
	// - PUT : "/{id}"
	// ----------------------------------------------------------------------------

	
	@Operation(summary = "Replacing a user.")
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

		log.info("Demande de modification de compte : {}", scimUser);
			
		throw new ScimNotImplementedException("En attente d'implémentation");
	}
	
	
	// ----------------------------------------------------------------------------
	// - DELETE : "/{id}"
	// ----------------------------------------------------------------------------
	
	@Operation(summary = "Deleting a user.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "204", description = "User deleted.", content = { @Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404", description = "User not found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))})
			})
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(
			@Parameter(description = "User ID") @PathVariable String id
			) throws ScimException {
		
		log.info("Demande de suppression de compte : {}", id);
		
		throw new ScimNotImplementedException("En attente d'implémentation");
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
			) throws ScimException {
		
		log.info("Recherche d'une liste de comptes");		

		throw new ScimNotImplementedException("En attente d'implémentation");
	}



}
