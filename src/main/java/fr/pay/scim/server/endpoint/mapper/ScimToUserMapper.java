package fr.pay.scim.server.endpoint.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import fr.pay.scim.server.endpoint.entity.user.ScimEmail;
import fr.pay.scim.server.endpoint.entity.user.ScimPhoneNumber;
import fr.pay.scim.server.endpoint.entity.user.ScimUser;
import fr.pay.scim.server.service.entity.user.Email;
import fr.pay.scim.server.service.entity.user.PhoneNumber;
import fr.pay.scim.server.service.entity.user.User;

@Component
public class ScimToUserMapper {


    // ----------------------------------------------------------------------------
	// - email
	// ----------------------------------------------------------------------------

    public Email email(ScimEmail scimEmail) {

        return new Email()
                        .setValue(scimEmail.getValue())
                        .setDisplay(scimEmail.getDisplay())
                        .setType(scimEmail.getType())
                        .setPrimary(scimEmail.getPrimary());
    }


    public List<Email> emails(List<ScimEmail> scimEmails) {
        if (scimEmails != null) {
            return scimEmails					
                    .stream()
                    .map(e -> email(e))
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    
    // ----------------------------------------------------------------------------
	// - phoneNumber
	// ----------------------------------------------------------------------------

    public PhoneNumber phoneNumber(ScimPhoneNumber scimPhoneNumber) {

        return new PhoneNumber()
                        .setValue(scimPhoneNumber.getValue())
                        .setDisplay(scimPhoneNumber.getDisplay())
                        .setType(scimPhoneNumber.getType())
                        .setPrimary(scimPhoneNumber.getPrimary());
    }
    
    public List<PhoneNumber> phoneNumbers(List<ScimPhoneNumber> scimPhoneNumbers) {
        if (scimPhoneNumbers != null) {
			return scimPhoneNumbers
					.stream()
					.map(phone -> phoneNumber(phone))
					.collect(Collectors.toList());
		}
        return null;
    }


    // ----------------------------------------------------------------------------
	// - user
	// ----------------------------------------------------------------------------
	
    public User mapper(User user, ScimUser scimUser) {

        if(user == null) {
            user = new User();
        }

        // id			READ_ONLY

		user.setExternalId(scimUser.getExternalId());								// READ_WRITE
		user.setUserName(scimUser.getUserName());										// READ_WRITE

		if (scimUser.getName() != null) {
			user.setHonorificPrefix(scimUser.getName().getHonorificPrefix());						// READ_WRITE
			user.setFamilyName(scimUser.getName().getFamilyName());									// READ_WRITE
			user.setGivenName(scimUser.getName().getGivenName());									// READ_WRITE
		} else {
			user.setHonorificPrefix(null);
			user.setFamilyName(null);
			user.setGivenName(null);
		}

		user.setDisplayName(scimUser.getDisplayName());												// READ_WRITE
		user.setTitle(scimUser.getTitle());															// READ_WRITE
		user.setUserType(scimUser.getUserType());										// READ_WRITE
		user.setPreferredLanguage(scimUser.getPreferredLanguage());										// READ_WRITE
		user.setLocale(scimUser.getLocale());										// READ_WRITE
		user.setTimezone(scimUser.getTimezone());										// READ_WRITE
		user.setActive(scimUser.getActive());										// READ_WRITE
		user.setPassword(scimUser.getPassword());										// WRITE_ONLY

		// READ_WRITE
		user.setEmails(emails(scimUser.getEmails()));

		// READ_WRITE
		user.setPhoneNumbers(phoneNumbers(scimUser.getPhoneNumbers()));

		return user;
	}

}
