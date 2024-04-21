package fr.pay.scim.server.endpoint.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import fr.pay.scim.server.endpoint.entity.ScimMeta;
import fr.pay.scim.server.endpoint.entity.user.ScimEmail;
import fr.pay.scim.server.endpoint.entity.user.ScimName;
import fr.pay.scim.server.endpoint.entity.user.ScimPhoneNumber;
import fr.pay.scim.server.endpoint.entity.user.ScimUser;
import fr.pay.scim.server.endpoint.entity.user.ScimUserGroup;
import fr.pay.scim.server.service.entity.user.Email;
import fr.pay.scim.server.service.entity.user.PhoneNumber;
import fr.pay.scim.server.service.entity.user.User;
import io.micrometer.common.util.StringUtils;

@Component
public class UserToScimMapper {

    // ----------------------------------------------------------------------------
	// - meta
	// ----------------------------------------------------------------------------

    public ScimMeta meta(User user, String location) {
		ScimMeta scimMeta = new ScimMeta();
		scimMeta.setResourceType("User");
		scimMeta.setLocation(location);
		scimMeta.setCreated(user.getCreated());
		scimMeta.setLastModified(user.getLastModified());
		return scimMeta;
    }

    // ----------------------------------------------------------------------------
	// - name
	// ----------------------------------------------------------------------------

    public ScimName name(User user) {
        
        if (StringUtils.isNotBlank(user.getHonorificPrefix()) 
                || StringUtils.isNotBlank(user.getFamilyName()) 
                || StringUtils.isNotBlank(user.getGivenName())) {
            
            ScimName scimName = new ScimName();
            scimName.setHonorificPrefix(user.getHonorificPrefix());
            scimName.setFamilyName(user.getFamilyName());
            scimName.setGivenName(user.getGivenName());

            return scimName;															
        }
        return null;
    }


    // ----------------------------------------------------------------------------
	// - email
	// ----------------------------------------------------------------------------

    public ScimEmail email(Email email) {
        return new ScimEmail()
                        .setValue(email.getValue())
                        .setDisplay(email.getDisplay())
                        .setType(email.getType())
                        .setPrimary(email.getPrimary());
    }
    
    public List<ScimEmail> emails(List<Email> emails) {
        if (emails != null) {
            return emails					
                    .stream()
                    .map(e -> email(e))
                    .collect(Collectors.toList());
        }
        return null;
    }
    
    // ----------------------------------------------------------------------------
	// - phoneNumber
	// ----------------------------------------------------------------------------

    public ScimPhoneNumber phoneNumber(PhoneNumber phoneNumber) {

        return new ScimPhoneNumber()
                        .setValue(phoneNumber.getValue())
                        .setDisplay(phoneNumber.getDisplay())
                        .setType(phoneNumber.getType())
                        .setPrimary(phoneNumber.getPrimary());
    }
    
    public List<ScimPhoneNumber> phoneNumbers(List<PhoneNumber> phoneNumbers) {
        if (phoneNumbers != null) {
			return phoneNumbers
					.stream()
					.map(phone -> phoneNumber(phone))
					.collect(Collectors.toList());
		}
        return null;
    }

    

    // ----------------------------------------------------------------------------
	// - scimUser
	// ----------------------------------------------------------------------------

    public ScimUser mapper(User user, String location) {
        ScimUser scimUser = new ScimUser();
		
		scimUser.setSchemas(Arrays.asList("urn:ietf:params:scim:schemas:core:2.0:User"));		// READ_WRITE

		// READ_ONLY
		scimUser.setMeta(meta(user, location));																	

		scimUser.setId(user.getId());																// READ_ONLY
		scimUser.setExternalId(user.getExternalId());												// READ_WRITE
		scimUser.setUserName(user.getUserName());													// READ_WRITE

		// READ_WRITE
		scimUser.setName(name(user));																

		scimUser.setDisplayName(user.getDisplayName());												// READ_WRITE
		scimUser.setTitle(user.getTitle());												// READ_WRITE
		scimUser.setUserType(user.getUserType());										// READ_WRITE
		scimUser.setPreferredLanguage(user.getPreferredLanguage());										// READ_WRITE
		scimUser.setLocale(user.getLocale());										// READ_WRITE
		scimUser.setTimezone(user.getTimezone());										// READ_WRITE
		scimUser.setActive(user.getActive());										// READ_WRITE
		// scimUser.setPassword(user.getPassword());								// WRITE_ONLY

		// READ_WRITE
		scimUser.setEmails(emails(user.getEmails()));

		// READ_WRITE
		scimUser.setPhoneNumbers(phoneNumbers(user.getPhoneNumbers()));

		if (user.getGroups() != null) {					
			scimUser.setGroups(user.getGroups()					// READ_ONLY
					.stream()
					.map(userGroup-> new ScimUserGroup()
								.setValue(userGroup.getValue())
								.setDisplay(userGroup.getDisplay())
								.setType(userGroup.getType()))
					.collect(Collectors.toList()));
		}

		return scimUser;
    }

}
