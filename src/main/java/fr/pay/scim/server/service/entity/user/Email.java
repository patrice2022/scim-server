package fr.pay.scim.server.service.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data()
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Email {

	private String value;

	private String display;

	private String type;

	private Boolean primary;

}
