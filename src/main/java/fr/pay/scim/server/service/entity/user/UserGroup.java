package fr.pay.scim.server.service.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data()
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup {

	/**
	 * The identifier of the User's group.
	 */
	private String value;

	/**
	 * A human-readable name, primarily used for display purposes. READ-ONLY.
	 */
	private String display;

	/**
	 * A label indicating the attribute's function, e.g., 'direct' or 'indirect'.
	 */
	private String type;

}
