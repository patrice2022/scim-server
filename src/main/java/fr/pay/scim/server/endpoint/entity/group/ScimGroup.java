package fr.pay.scim.server.endpoint.entity.group;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import fr.pay.scim.server.endpoint.entity.ScimResource;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "group")
public class ScimGroup extends ScimResource {

	/**
	 * A human-readable name for the Group. REQUIRED.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private String displayName;

		
	/**
	 * A list of members of the Group.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private List<ScimMember> members;
}
