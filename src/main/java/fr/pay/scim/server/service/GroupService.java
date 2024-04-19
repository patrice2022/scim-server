package fr.pay.scim.server.service;

import fr.pay.scim.server.service.entity.group.Group;
import fr.pay.scim.server.service.entity.group.Groups;

public interface GroupService {

    public Group findGroupByDisplayName(String displayName);

    public Group createGroup(Group group);

    public Group findGroup(String id);

    public Group updateGroup(Group group);

    public void delete(String id);

    public Groups findGroups(String filter, String attributes, String excludedAttributes, String sortBy,
            String sortOrder, int startIndex, int count);

}
