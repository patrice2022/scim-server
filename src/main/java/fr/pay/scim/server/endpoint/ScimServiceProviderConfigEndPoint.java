package fr.pay.scim.server.endpoint;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.ForwardedHeaderUtils;

import fr.pay.scim.server.endpoint.entity.ScimResources;
import fr.pay.scim.server.service.ProviderConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/scim/v2/ServiceProviderConfig")
public class ScimServiceProviderConfigEndPoint {

	@Autowired
	private ProviderConfigService providerConfigService;


	// ========================================================
	// = location
	// ========================================================

	private String location(HttpServletRequest request) throws URISyntaxException {
		
		URI url = new URI(request.getRequestURL().toString());
		
		ServletServerHttpRequest sshr = new ServletServerHttpRequest(request);

		String location = ForwardedHeaderUtils.adaptFromForwardedHeaders(url, sshr.getHeaders())
				.build()
				.toUriString();
		
		return location;
	}	




    // ========================================================
	// = Search
	// ========================================================

	// ----------------------------------------------------------------------------
	// - GET : ""
	// ----------------------------------------------------------------------------
	
    @Operation(summary = "Search config")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "config trouvée", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimResources.class))})
			})
	@GetMapping("")
	public ResponseEntity<Map<String, Object>> findConfig(
			HttpServletRequest request
			) throws Exception {
		
		log.info("ScimServiceProviderConfigEndPoint");		

        Map<String, Object> config = new LinkedHashMap<>();		
     
        List<String> schemas = new ArrayList<>();
        schemas.add("urn:ietf:params:scim:schemas:core:2.0:ServiceProviderConfig");
        config.put("schemas", schemas);

        // config.put("documentationUri", "http://example.com/help/scim.html");

        Map<String, Object> patch = new LinkedHashMap<>();
        patch.put("supported", providerConfigService.patchSupported());
        config.put("patch", patch);

        Map<String, Object> bulk = new LinkedHashMap<>();
        bulk.put("supported", providerConfigService.bulkSupported());
        if (providerConfigService.bulkSupported()) {
            bulk.put("maxOperations", providerConfigService.bulkMaxOperations());
            bulk.put("maxPayloadSize", providerConfigService.bulkMaxPayloadSize());
        }
        config.put("bulk", bulk);

        Map<String, Object> filter = new LinkedHashMap<>();
        filter.put("supported", providerConfigService.filterSupported());
        if (providerConfigService.filterSupported()) {
            filter.put("maxResults", providerConfigService.filterMaxResults());
        }
        config.put("filter", filter);

        Map<String, Object> changePassword = new LinkedHashMap<>();
        changePassword.put("changePassword", providerConfigService.changePassword());
        config.put("changePassword", changePassword);

        Map<String, Object> sort = new LinkedHashMap<>();
        sort.put("supported", providerConfigService.sortSupported());
        config.put("sort", sort);

        Map<String, Object> etag = new LinkedHashMap<>();
        etag.put("supported", providerConfigService.etagSupported());
        config.put("etag", etag);

        Map<String, Object> Schemes1 = new LinkedHashMap<>();
        Schemes1.put("type", "httpbasic");
        Schemes1.put("name", "HTTP Basic");
        Schemes1.put("description", "Authentication scheme using the HTTP Basic Standard");
        Schemes1.put("specUri", "http://www.rfc-editor.org/info/rfc2617");
        // Schemes1.put("documentationUri", "NC");

        List<Map<String, Object>> authenticationSchemes = new ArrayList<>();
        authenticationSchemes.add(Schemes1);
        config.put("authenticationSchemes", authenticationSchemes);

        Map<String, Object> meta = new LinkedHashMap<>();
        meta.put("location", location(request));
        meta.put("resourceType", "ServiceProviderConfig");
        meta.put("created", "2024-05-09T22:56:22Z");
        meta.put("lastModified", "2024-05-09T22:56:22Z");
        // meta.put("version", "W\/\"3694e05e9dff594\"");
        config.put("meta", meta);
  
 		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Content-Type", "application/scim+json")
				.body(config);
		
		
	}
}
