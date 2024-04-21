package fr.pay.scim.server.endpoint.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.pay.scim.server.endpoint.entity.user.ScimEmail;
import fr.pay.scim.server.endpoint.entity.user.ScimPhoneNumber;
import fr.pay.scim.server.service.entity.user.Email;
import fr.pay.scim.server.service.entity.user.PhoneNumber;

public class ScimToUserMapperTest {

    private ScimToUserMapper mapper = new ScimToUserMapper();

    
    // ----------------------------------------------------------------------------
	// - email
	// ----------------------------------------------------------------------------

    @Test
	@DisplayName("Test du mapper 'mail' scim -> user")
	public void mapper_mail_scim_to_user() {

        String value = "value";
		String display = "display";
		String type = "type";
        Boolean primary = Boolean.TRUE;
		
		ScimEmail scimEmail = new ScimEmail()        
                        .setValue(value)
                        .setDisplay(display)
                        .setType(type)
                        .setPrimary(primary);

		Email email = mapper.email(scimEmail);

		assertAll("email", 
				() -> assertEquals(value, email.getValue()),
				() -> assertEquals(display, email.getDisplay()),
				() -> assertEquals(type, email.getType()),
				() -> assertEquals(primary, email.getPrimary())
		);
	}


    // ----------------------------------------------------------------------------
	// - phoneNumber
	// ----------------------------------------------------------------------------

    @Test
	@DisplayName("Test du mapper 'phoneNumber' user -> scim")
	public void mapper_phoneNumber_scim_to_user() {

        String value = "value";
		String display = "display";
		String type = "type";
        Boolean primary = Boolean.TRUE;
		
		ScimPhoneNumber scimPhoneNumber = new ScimPhoneNumber()        
                        .setValue(value)
                        .setDisplay(display)
                        .setType(type)
                        .setPrimary(primary);

		PhoneNumber phoneNumber = mapper.phoneNumber(scimPhoneNumber);

		assertAll("phoneNumber", 
				() -> assertEquals(value, phoneNumber.getValue()),
				() -> assertEquals(display, phoneNumber.getDisplay()),
				() -> assertEquals(type, phoneNumber.getType()),
				() -> assertEquals(primary, phoneNumber.getPrimary())
		);
	}

}
