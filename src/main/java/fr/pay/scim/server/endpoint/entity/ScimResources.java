package fr.pay.scim.server.endpoint.entity;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data()
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Schema(name = "Resources")
public class ScimResources {

    private long totalResults;
	
	private int itemsPerPage;
	
	private int startIndex;
	
	private List<String> schemas = Arrays.asList("urn:ietf:params:scim:api:messages:2.0:ListResponse");
	
	@JsonProperty(value = "Resources")
	private List<? extends ScimResource> resources;
}
