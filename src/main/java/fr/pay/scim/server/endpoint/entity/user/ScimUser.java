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
	
	
	/**
	 * The casual way to address the user in real life, e.g., "Bob" or "Bobby"
	 * instead of "Robert". This attribute SHOULD NOT be used to represent a User's
	 * username (e.g., bjensen or mpepperidge).
	 * 
	 * <pre>
	 * 		"name" : "nickName",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The casual way to address the user in real
	 * 			life, e.g., 'Bob' or 'Bobby' instead of 'Robert'.  This attribute
	 * 			SHOULD NOT be used to represent a User's username (e.g., 'bjensen' or
	 * 			'mpepperidge').",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	// @Schema(name = "nickName",
	// 		type = "string",
	// 		description = "The casual way to address the user in real life, e.g., \"Bob\" or \"Bobby\""
	// 				+ "	instead of \"Robert\". This attribute SHOULD NOT be used to represent a User's"
	// 				+ "	username (e.g., bjensen or mpepperidge).",
	// 		example = "Bobby",
	// 		requiredMode = RequiredMode.NOT_REQUIRED,
	// 		accessMode = Schema.AccessMode.READ_WRITE
	// 		)
	// private String nickName;	


	/**
	 * A URI that is a uniform resource locator (as defined in Section 1.1.3 of
	 * [RFC3986]) and that points to a location representing the user's online
	 * profile (e.g., a web page). URIs are canonicalized per Section 6.2 of
	 * [RFC3986].
	 * 
	 * <pre>
	 * 		"name" : "profileUrl",
	 * 		"type" : "reference",
	 * 		"referenceTypes" : ["external"],
	 * 		"multiValued" : false,
	 * 		"description" : "A fully qualified URL pointing to a page
	 * 			representing the User's online profile.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
//	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
//	private URI profileUrl;
	
	
	/**
	 * The user's title, such as "Vice President".
	 * 
	 * <pre>
	 * 		"name" : "title",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The user's title, such as
	 * 			"Vice President.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	@Schema(name = "title",
			type = "string",
			description = "The user's title, such as \"Vice President\".",
			example = "Vice President",
			requiredMode = RequiredMode.NOT_REQUIRED,
			accessMode = Schema.AccessMode.READ_WRITE
			)
	private String title;

	
	/**
	 * Used to identify the relationship between the organization and the user.
	 * Typical values used might be "Contractor", "Employee", "Intern", "Temp",
	 * "External", and "Unknown", but any value may be used.
	 * 
	 * <pre>
	 * 		"name" : "userType",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "Used to identify the relationship between
	 * 				the organization and the user.  Typical values used might be
	 * 				'Contractor', 'Employee', 'Intern', 'Temp', 'External', and
	 * 				'Unknown', but any value may be used.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	@Schema(name = "userType",
			type = "string",
			description = "Used to identify the relationship between the organization and the user."
					+ "	Typical values used might be \"Contractor\", \"Employee\", \"Intern\", \"Temp\","
					+ "	\"External\", and \"Unknown\", but any value may be used.",
			example = "Employee",
			requiredMode = RequiredMode.NOT_REQUIRED,
			accessMode = Schema.AccessMode.READ_WRITE
			)
	private String userType;
	
	
	/**
	 * Indicates the user's preferred written or spoken languages and is generally
	 * used for selecting a localized user interface. The value indicates the set of
	 * natural languages that are preferred. The format of the value is the same as
	 * the HTTP Accept-Language header field (not including "Accept-Language:") and
	 * is specified in Section 5.3.5 of [RFC7231]. The intent of this value is to
	 * enable cloud applications to perform matching of language tags [RFC4647] to
	 * the user's language preferences, regardless of what may be indicated by a
	 * user agent (which might be shared), or in an interaction that does not
	 * involve a user (such as in a delegated OAuth 2.0 [RFC6749] style interaction)
	 * where normal HTTP Accept-Language header negotiation cannot take place.
	 * 
	 * <pre>
	 * 		"name" : "preferredLanguage",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "Indicates the User's preferred written or
	 * 			spoken language.  Generally used for selecting a localized user
	 * 			interface; e.g., 'en_US' specifies the language English and country
	 * 			US.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	@Schema(name = "preferredLanguage",
			type = "string",
			description = "Indicates the user's preferred written or spoken languages and is generally"
					+ "	used for selecting a localized user interface. The value indicates the set of"
					+ "	natural languages that are preferred. The format of the value is the same as"
					+ "	the HTTP Accept-Language header field (not including \"Accept-Language:\") and"
					+ "	is specified in Section 5.3.5 of [RFC7231]. The intent of this value is to"
					+ "	enable cloud applications to perform matching of language tags [RFC4647] to"
					+ "	the user's language preferences, regardless of what may be indicated by a"
					+ "	user agent (which might be shared), or in an interaction that does not"
					+ "	involve a user (such as in a delegated OAuth 2.0 [RFC6749] style interaction)"
					+ "	where normal HTTP Accept-Language header negotiation cannot take place.",
			example = "fr-FR",
			requiredMode = RequiredMode.NOT_REQUIRED,
			accessMode = Schema.AccessMode.READ_WRITE
			)
	private String preferredLanguage;
	
	
	/**
	 * Used to indicate the User's default location for purposes of localizing such
	 * items as currency, date time format, or numerical representations. A valid
	 * value is a language tag as defined in [RFC5646]. Computer languages are
	 * explicitly excluded.
	 * 
	 * A language tag is a sequence of one or more case-insensitive sub-tags, each
	 * separated by a hyphen character ("-", %x2D). For backward compatibility,
	 * servers MAY accept tags separated by an underscore character ("_", %x5F). In
	 * most cases, a language tag consists of a primary language sub-tag that
	 * identifies a broad family of related languages (e.g., "en" = English) and
	 * that is optionally followed by a series of sub-tags that refine or narrow
	 * that language's range (e.g., "en-CA" = the variety of English as communicated
	 * in Canada). Whitespace is not allowed within a language tag. Example tags
	 * include:
	 * 
	 * fr, en-US, es-419, az-Arab, x-pig-latin, man-Nkoo-GN
	 * 
	 * See [RFC5646] for further information.
	 * 
	 * <pre>
	 * 		"name" : "locale",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "Used to indicate the User's default location
	 * 			for purposes of localizing items such as currency, date time format, or
	 * 			numerical representations.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	@Schema(name = "locale",
			type = "string",
			description = "Used to indicate the User's default location for purposes of localizing such"
					+ "	items as currency, date time format, or numerical representations. A valid"
					+ "	value is a language tag as defined in [RFC5646]. Computer languages are"
					+ "	explicitly excluded.",
			example = "fr-FR",
			requiredMode = RequiredMode.NOT_REQUIRED,
			accessMode = Schema.AccessMode.READ_WRITE
			)
	private String locale;

	
	/**
	 * The User's time zone, in IANA Time Zone database format [RFC6557], also known
	 * as the "Olson" time zone database format [Olson-TZ] (e.g.,
	 * "America/Los_Angeles").
	 * 
	 * <pre>
	 * 		"name" : "timezone",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The User's time zone in the 'Olson' time zone
	 * 			database format, e.g., 'America/Los_Angeles'.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	@Schema(name = "timezone",
			type = "string",
			description = "The User's time zone, in IANA Time Zone database format [RFC6557], also known"
					+ "	as the \"Olson\" time zone database format [Olson-TZ] (e.g.,"
					+ "	\"America/Los_Angeles\").",
			example = "America/Los_Angeles",
			requiredMode = RequiredMode.NOT_REQUIRED,
			accessMode = Schema.AccessMode.READ_WRITE
			)
	private String timezone;	
	
	
	/**
	 * A Boolean value indicating the user's administrative status. The definitive
	 * meaning of this attribute is determined by the service provider. As a typical
	 * example, a value of true implies that the user is able to log in, while a
	 * value of false implies that the user's account has been suspended.
	 * 
	 * <pre>
	 * 		"name" : "active",
	 * 		"type" : "boolean",
	 * 		"multiValued" : false,
	 * 		"description" : "A Boolean value indicating the User's
	 * 			administrative status.",
	 * 		"required" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default
	 * </pre>
	 */
	@Schema(name = "active",
			type = "boolean",
			description = "A Boolean value indicating the User's administrative status.",
			example = "true",
			requiredMode = RequiredMode.NOT_REQUIRED,
			accessMode = Schema.AccessMode.READ_WRITE
			)
	private Boolean active;
	
	


}
