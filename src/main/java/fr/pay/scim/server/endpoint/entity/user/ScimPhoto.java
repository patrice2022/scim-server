package fr.pay.scim.server.endpoint.entity.user;

import java.net.URI;

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
@Schema(name = "photo")
public class ScimPhoto {

	/**
	 * URL of a photo of the User.
	 * 
	 * <pre>
	 * 		"name" : "value",
	 * 		"type" : "reference",
	 * 		"referenceTypes" : ["external"],
	 * 		"multiValued" : false,
	 * 		"description" : "URL of a photo of the User.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
 	 * 		"returned" : "default",
 	 * 		"uniqueness" : "none"
 	 * </pre>
	 */
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private URI value;

	
	/**
	 * A human-readable name, primarily used for display purposes.
	 * 
	 *  <pre>
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
	 *  </pre>
	 */
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private String display;

	
	/**
	 * A label indicating the attribute's function, i.e., 'photo' or 'thumbnail'.
	 * 
	 * <pre>
	 * 		"name" : "type",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "A label indicating the attribute's
	 * 			function, i.e., 'photo' or 'thumbnail'.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"canonicalValues" : [
	 * 			"photo",
	 * 			"thumbnail"
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
	 * this attribute, e.g., the preferred photo or thumbnail. The primary attribute
	 * value 'true' MUST appear no more than once.
	 * 
	 * 		"name" : "primary",
	 * 		"type" : "boolean",
	 * 		"multiValued" : false,
	 * 		"description" : "A Boolean value indicating the 'primary'
	 * 			or preferred attribute value for this attribute, e.g., the preferred
	 * 			photo or thumbnail.  The primary attribute value 'true' MUST appear
	 * 			no more than once.",
	 * 		"required" : false,
	 * 		mutability" : "readWrite",
	 * 		"returned" : "default"
	 */
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private Boolean primary;
	
}
