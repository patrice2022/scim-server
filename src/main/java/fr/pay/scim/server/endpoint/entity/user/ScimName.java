package fr.pay.scim.server.endpoint.entity.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * The components of the user's name. Service providers MAY return just the full
 * name as a single string in the formatted sub-attribute, or they MAY return
 * just the individual component attributes using the other sub-attributes, or
 * they MAY return both. If both variants are returned, they SHOULD be
 * describing the same name, with the formatted name indicating how the
 * component attributes should be combined.
 * 
 * @author PAUBRY
 */
@Data()
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Schema(name = "name")
public class ScimName {

	/**
	 * The full name, including all middle names, titles, and suffixes as
	 * appropriate, formatted for display (e.g., "Ms. Barbara Jane Jensen, III").
	 * 
	 * <pre>
	 * 		"name" : "formatted",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The full name, including all middle
	 * 			names, titles, and suffixes as appropriate, formatted for display
	 * 			(e.g., 'Ms. Barbara J Jensen, III').",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	// @Schema(name = "formatted",
	// 		type = "string",
	// 		description = "The full name, including all middle names, titles, and suffixes as appropriate, formatted for display (e.g., 'Ms. Barbara J Jensen, III').",
	// 		example = "Ms. Barbara J Jensen, III",
	// 		requiredMode = RequiredMode.NOT_REQUIRED,
	// 		accessMode = Schema.AccessMode.READ_WRITE
	// 		)
	// private String formatted;

	
	/**
	 * The family name of the User, or last name in most Western languages (e.g.,
	 * "Jensen" given the full name "Ms. Barbara Jane Jensen, III").
	 * 
	 * <pre>
	 * 		"name" : "familyName",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The family name of the User, or
	 * 			last name in most Western languages (e.g., 'Jensen' given the full
	 * 			name 'Ms. Barbara J Jensen, III').",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	@Schema(name = "familyName",
			type = "string",
			description = "The family name of the User, or last name in most Western languages (e.g., 'Jensen' given the full name 'Ms. Barbara J Jensen, III').",
			example = "Jensen",
			requiredMode = RequiredMode.NOT_REQUIRED,
			accessMode = Schema.AccessMode.READ_WRITE
			)
	private String familyName;

	
	/**
	 * The given name of the User, or first name in most Western languages (e.g.,
	 * "Barbara" given the full name "Ms. Barbara Jane Jensen, III").
	 * 
	 * <pre>
	 * 		"name" : "givenName",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The given name of the User, or
	 * 			first name in most Western languages (e.g., 'Barbara' given the
	 * 			full name 'Ms. Barbara J Jensen, III').",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	@Schema(name = "givenName",
			type = "string",
			description = "The given name of the User, or first name in most Western languages (e.g., 'Barbara' given the full name 'Ms. Barbara J Jensen, III').",
			example = "Barbara",
			requiredMode = RequiredMode.NOT_REQUIRED,
			accessMode = Schema.AccessMode.READ_WRITE
			)
	private String givenName;

	
	/**
	 * The middle name(s) of the User (e.g., "Jane" given the full name "Ms. Barbara
	 * Jane Jensen, III").
	 * 
	 * <pre>
	 * 		"name" : "middleName",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The middle name(s) of the User
	 * 			(e.g., 'Jane' given the full name 'Ms. Barbara J Jensen, III').",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	// @Schema(name = "middleName",
	// 		type = "string",
	// 		description = "The middle name(s) of the User (e.g., 'Jane' given the full name 'Ms. Barbara J Jensen, III').",
	// 		example = "Jane",
	// 		requiredMode = RequiredMode.NOT_REQUIRED,
	// 		accessMode = Schema.AccessMode.READ_WRITE
	// 		)
	// private String middleName;

	
	/**
	 * The honorific prefix(es) of the User, or title in most Western languages
	 * (e.g., "Ms." given the full name "Ms. Barbara Jane Jensen, III").
	 * 
	 * <pre>
	 * 		"name" : "honorificPrefix",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The honorific prefix(es) of the User, or
	 * 			title in most Western languages (e.g., 'Ms.' given the full name
	 * 			'Ms. Barbara J Jensen, III').",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	@Schema(name = "honorificPrefix",
			type = "string",
			description = "The honorific prefix(es) of the User, or title in most Western languages (e.g., 'Ms.' given the full name 'Ms. Barbara J Jensen, III').",
			example = "Ms.",
			requiredMode = RequiredMode.NOT_REQUIRED,
			accessMode = Schema.AccessMode.READ_WRITE
			)
	private String honorificPrefix;

	
	/**
	 * The honorific suffix(es) of the User, or suffix in most Western languages
	 * (e.g., "III" given the full name "Ms. Barbara Jane Jensen, III").
	 * 	 
	 * <pre>
	 * 		"name" : "honorificSuffix",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The honorific suffix(es) of the User, or
	 * 			suffix in most Western languages (e.g., 'III' given the full name
	 * 			'Ms. Barbara J Jensen, III').",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	// @Schema(name = "honorificSuffix",
	// 		type = "string",
	// 		description = "The honorific suffix(es) of the User, or suffix in most Western languages (e.g., 'III' given the full name 'Ms. Barbara J Jensen, III').",
	// 		example = "III",
	// 		requiredMode = RequiredMode.NOT_REQUIRED,
	// 		accessMode = Schema.AccessMode.READ_WRITE
	// 		)
	// private String honorificSuffix;

}