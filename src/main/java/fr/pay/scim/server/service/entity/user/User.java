package fr.pay.scim.server.service.entity.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data()
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String externalId;

    private String userName;

    private String honorificPrefix;

    private String familyName;

    private String givenName;


    
    private Date created;

    private Date lastModified;

}
