package fr.pay.scim.server.endpoint.entity.group;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data()
@Accessors(chain = true)
@JsonInclude(Include.NON_NULL)
@Schema(name = "member")
public class ScimMember {

    /**
	 * Identifier of the member of this Group.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String value;

	
	/**
	 * A human-readable name.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String display;
	
	
	/**
	 * The URI corresponding to a SCIM resource that is a member of this Group.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	@JsonProperty(value = "$ref")
	private String ref;

	/**
	 * A label indicating the type of resource, e.g., 'User' or 'Group'.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String type;

}
