package fr.pay.scim.server.endpoint.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@Schema(name = "resource")
public class ScimResource {

	/**
	 * SCIM provides a resource type for "User" resources. The core schema for
	 * "User" is identified using the following schema URI:
	 * "urn:ietf:params:scim:schemas:core:2.0:User".
	 */
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private List<String> schemas;
	
	
	/**
	 * A complex attribute containing resource metadata. All "meta" sub-attributes
	 * are assigned by the service provider (have a "mutability" of "readOnly"), and
	 * all of these sub-attributes have a "returned" characteristic of "default".
	 * 
	 * This attribute SHALL be ignored when provided by clients.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private ScimMeta meta;

	
    /**
	 * A unique identifier for a SCIM resource as defined by the service provider.
	 * Each representation of the resource MUST include a non-empty "id" value. This
	 * identifier MUST be unique across the SCIM service provider's entire set of
	 * resources. It MUST be a stable, non-reassignable identifier that does not
	 * change when the same resource is returned in subsequent requests. The value
	 * of the "id" attribute is always issued by the service provider and MUST NOT
	 * be specified by the client. The string "bulkId" is a reserved keyword and
	 * MUST NOT be used within any unique identifier value. 
	 * 
	 * The attribute characteristics are "caseExact" as "true", a mutability of "readOnly", and a
	 * "returned" characteristic of "always".
	 */
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String id;

		
	
	/**
	 * A String that is an identifier for the resource as defined by the
	 * provisioning client. The "externalId" may simplify identification of a
	 * resource between the provisioning client and the service provider by allowing
	 * the client to use a filter to locate the resource with an identifier from the
	 * provisioning domain, obviating the need to store a local mapping between the
	 * provisioning domain's identifier of the resource and the identifier used by
	 * the service provider. Each resource MAY include a non-empty "externalId"
	 * value. The value of the "externalId" attribute is always issued by the
	 * provisioning client and MUST NOT be specified by the service provider. The
	 * service provider MUST always interpret the externalId as scoped to the
	 * provisioning domain. While the server does not enforce uniqueness, it is
	 * assumed that the value's uniqueness is controlled by the client setting the
	 * value.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private String externalId;

}
