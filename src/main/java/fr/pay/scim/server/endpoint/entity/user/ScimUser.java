package fr.pay.scim.server.endpoint.entity.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import fr.pay.scim.server.endpoint.entity.ScimResource;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data()
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Schema(name = "user")
public class ScimUser extends ScimResource {

	/**
	 * Unique identifier for the User, typically
	 * used by the user to directly authenticate to the service provider.
	 * Each User MUST include a non-empty userName value.  This identifier
	 * MUST be unique across the service provider's entire set of Users.
	 * REQUIRED.
	 */
	@Schema(name = "userName",
			type = "string",
			description = "Unique identifier for the User, typically"
					+ " used by the user to directly authenticate to the service provider."
					+ " Each User MUST include a non-empty userName value.  This identifier"
					+ " MUST be unique across the service provider's entire set of Users.",
			example = "bjensen@example.com",
			requiredMode = RequiredMode.REQUIRED,
			accessMode = Schema.AccessMode.READ_WRITE
			)
	private String userName;

}
