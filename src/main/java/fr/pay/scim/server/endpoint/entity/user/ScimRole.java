package fr.pay.scim.server.endpoint.entity.user;

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
@Schema(name = "role")
public class ScimRole {

	/**
	 * The value of a role.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private String value;

	
	/**
	 * A human-readable name, primarily used for display purposes. READ-ONLY.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private String display;

	
	/**
	 * A label indicating the attribute's function.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private String type;

	
	/**
	 * A Boolean value indicating the 'primary' or preferred attribute value for
	 * this attribute. The primary attribute value 'true' MUST appear no more than
	 * once.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private Boolean primary;
}
