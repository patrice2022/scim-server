package fr.pay.scim.server.endpoint.entity.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * ScimEmail addresses for the User. The value SHOULD be specified according
 * to[RFC5321]. Service providers SHOULD canonicalize the value according
 * to[RFC5321], e.g., "bjensen@example.com" instead of "bjensen@EXAMPLE.COM".
 * 
 * @author PAUBRY
 */
@Data()
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Schema(name = "email")
public class ScimEmail {

	/**
	 * E-mail addresses for the user. The value SHOULD be canonicalized by the
	 * Service Provider, e.g. bjensen@example.com instead of bjensen@EXAMPLE.COM.
	 * Canonical Type values of work, home, and other.
	 * 
	 * <pre>
	 * 		"name" : "value",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "Email addresses for the user.  The value
	 * 			SHOULD be canonicalized by the service provider, e.g.,
	 * 			'bjensen@example.com' instead of 'bjensen@EXAMPLE.COM'.
	 * 			Canonical type values of 'work', 'home', and 'other'.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	@Schema(name = "value",
			type = "string",
			description = "Email addresses for the user.  The value"
					+ "	SHOULD be canonicalized by the service provider, e.g.,"
					+ "	'bjensen@example.com' instead of 'bjensen@EXAMPLE.COM'."
					+ "	Canonical type values of 'work', 'home', and 'other'.",
			example = "bjensen@example.com",
			requiredMode = RequiredMode.NOT_REQUIRED,
			accessMode = Schema.AccessMode.READ_WRITE
			)
	@Pattern(regexp = "[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$")
	private String value;

	
	/**
	 * The "display" sub-attribute MAY be used to return the canonicalized
	 * representation of the email value.
	 * 
	 * <pre>
	 * 		"name" : "display",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "A human-readable name, primarily used
	 * 			for display purposes.  READ-ONLY.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"	 
	 * </pre>
	 */
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private String display;

	
	/**
	 * The "type" sub-attribute is used to provide a classification meaningful to
	 * the (human) user. The user interface should encourage the use of basic values
	 * of "work", "home", and "other" and MAY allow additional type values to be
	 * used at the discretion of SCIM clients.
	 * 
	 * <pre>
	 * 		"name" : "type",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "A label indicating the attribute's
	 * 			function, e.g., 'work' or 'home'.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"canonicalValues" : [
	 * 			"work",
	 * 			"home",
	 * 			"other"
	 * 		],
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private String type;

	
	/**
	 * A Boolean value indicating the 'primary' or preferred attribute value for
	 * this attribute, e.g. the preferred mailing address or primary e-mail address.
	 * The primary attribute value 'true' MUST appear no more than once.
	 * 
	 * <pre>
	 * 		"name" : "primary",
	 * 		"type" : "boolean",
	 * 		"multiValued" : false,
	 * 		"description" : "A Boolean value indicating the 'primary'
	 * 			or preferred attribute value for this attribute, e.g., the preferred
	 * 			mailing address or primary email address.  The primary attribute
	 * 			value 'true' MUST appear no more than once.",
	 * 		"required" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default"
	 * </pre>
	 */
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private Boolean primary;

}
