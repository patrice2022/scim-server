package fr.pay.scim.server.service.entity.group;

import java.util.Date;
import java.util.List;

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

    private String externalId;

    private String displayName;

    private List<String> members;

    private Date created;

    private Date lastModified;
}
