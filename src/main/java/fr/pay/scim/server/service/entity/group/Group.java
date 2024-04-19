package fr.pay.scim.server.service.entity.group;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data()
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    private String id;

    private String displayName;

    private Date created;

    private Date lastModified;
}