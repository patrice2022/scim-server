package fr.pay.scim.server.endpoint.entity.user;

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
@Schema(name = "group")
public class ScimUserGroup {

	/**
	 * The identifier of the User's group.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String value;

	/**
	 * The URI of the corresponding 'Group' resource to which the user belongs.
	 */
	@JsonProperty(value = "$ref")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String ref;

	/**
	 * A human-readable name, primarily used for display purposes. READ-ONLY.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String display;

	/**
	 * A label indicating the attribute's function, e.g., 'direct' or 'indirect'.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String type;

}
