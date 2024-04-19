package fr.pay.scim.server.endpoint.entity.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import fr.pay.scim.server.endpoint.entity.ScimResource;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
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

	
	/**
	 * The components of the user's real name.
	 * Providers MAY return just the full name as a single string in the
	 * formatted sub-attribute, or they MAY return just the individual
	 * component attributes using the other sub-attributes, or they MAY
	 * return both.  If both variants are returned, they SHOULD be
	 * describing the same name, with the formatted name indicating how the
	 * component attributes should be combined.
	 */
	@Schema(name = "name",
			description = "The components of the user's real name."
					+ "	Providers MAY return just the full name as a single string in the"
					+ "	formatted sub-attribute, or they MAY return just the individual"
					+ "	component attributes using the other sub-attributes, or they MAY"
					+ "	return both.  If both variants are returned, they SHOULD be"
					+ "	describing the same name, with the formatted name indicating how the"
					+ "	component attributes should be combined.",
			requiredMode = RequiredMode.NOT_REQUIRED,
			accessMode = Schema.AccessMode.READ_WRITE
			)
	private @Valid ScimName name;


	/**
	 * The name of the user, suitable for display to end-users. Each user returned
	 * MAY include a non-empty displayName value. The name SHOULD be the full name
	 * of the User being described, if known (e.g., "Babs Jensen" or "Ms. Barbara J
	 * Jensen, III") but MAY be a username or handle, if that is all that is
	 * available (e.g., "bjensen"). The value provided SHOULD be the primary textual
	 * label by which this User is normally displayed by the service provider when
	 * presenting it to end-users.
	 * 
	 * <pre>
	 * 		"name" : "displayName",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The name of the User, suitable for display
	 * 			to end-users.  The name SHOULD be the full name of the User being
	 * 			described, if known.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	@Schema(name = "displayName",
			type = "string",
			description = "The name of the user, suitable for display to end-users. Each user returned"
					+ "	MAY include a non-empty displayName value. The name SHOULD be the full name"
					+ "	of the User being described, if known (e.g., \"Babs Jensen\" or \"Ms. Barbara J"
					+ "	Jensen, III\") but MAY be a username or handle, if that is all that is"
					+ "	available (e.g., \"bjensen\"). The value provided SHOULD be the primary textual"
					+ "	label by which this User is normally displayed by the service provider when"
					+ "	presenting it to end-users.",
			example = "Babs Jensen",
			requiredMode = RequiredMode.NOT_REQUIRED,
			accessMode = Schema.AccessMode.READ_WRITE
			)
	private String displayName;
	
}
