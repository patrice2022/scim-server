package fr.pay.scim.server.endpoint.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.pay.scim.server.endpoint.entity.user.ScimEmail;
import fr.pay.scim.server.endpoint.entity.user.ScimName;
import fr.pay.scim.server.endpoint.entity.user.ScimPhoneNumber;
import fr.pay.scim.server.service.entity.user.Email;
import fr.pay.scim.server.service.entity.user.PhoneNumber;
import fr.pay.scim.server.service.entity.user.User;

public class UserToScimMapperTest {

    private UserToScimMapper mapper = new UserToScimMapper();

    // ----------------------------------------------------------------------------
	// - name
	// ----------------------------------------------------------------------------

	@Test
	@DisplayName("Test du mapper 'name' user -> scim")
	public void mapper_name_user_to_scim() {

		String familyName = "familyName";
		String givenName = "givenName";
		String honorificPrefix = "honorificPrefix";
		
		User user = new User()
				.setFamilyName(familyName)
				.setGivenName(givenName)
				.setHonorificPrefix(honorificPrefix);

		ScimName scimName = mapper.name(user);

		assertAll("name", 
				() -> assertEquals(familyName, scimName.getFamilyName()),
				() -> assertEquals(givenName, scimName.getGivenName()),
				() -> assertEquals(honorificPrefix, scimName.getHonorificPrefix())
		);
	}

    @Test
	@DisplayName("Test du mapper 'name' user -> scim, pas de donnée")
	public void mapper_name_user_to_scim2() {
		
		User user = new User();

		ScimName name = mapper.name(user);

        assertNull(name);
	}



    // ----------------------------------------------------------------------------
	// - email
	// ----------------------------------------------------------------------------

    @Test
	@DisplayName("Test du mapper 'mail' user -> scim")
	public void mapper_mail_user_to_scim() {

        String value = "value";
		String display = "display";
		String type = "type";
        Boolean primary = Boolean.TRUE;
		
		Email email = new Email()        
                        .setValue(value)
                        .setDisplay(display)
                        .setType(type)
                        .setPrimary(primary);

		ScimEmail scimEmail = mapper.email(email);

		assertAll("email", 
				() -> assertEquals(value, scimEmail.getValue()),
				() -> assertEquals(display, scimEmail.getDisplay()),
				() -> assertEquals(type, scimEmail.getType()),
				() -> assertEquals(primary, scimEmail.getPrimary())
		);
	}


    // ----------------------------------------------------------------------------
	// - phoneNumber
	// ----------------------------------------------------------------------------

    @Test
	@DisplayName("Test du mapper 'phoneNumber' user -> scim")
	public void mapper_phoneNumber_user_to_scim() {

        String value = "value";
		String display = "display";
		String type = "type";
        Boolean primary = Boolean.TRUE;
		
		PhoneNumber phoneNumber = new PhoneNumber()        
                        .setValue(value)
                        .setDisplay(display)
                        .setType(type)
                        .setPrimary(primary);

		ScimPhoneNumber scimPhoneNumber = mapper.phoneNumber(phoneNumber);

		assertAll("phoneNumber", 
				() -> assertEquals(value, scimPhoneNumber.getValue()),
				() -> assertEquals(display, scimPhoneNumber.getDisplay()),
				() -> assertEquals(type, scimPhoneNumber.getType()),
				() -> assertEquals(primary, scimPhoneNumber.getPrimary())
		);
	}



}
