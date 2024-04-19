package fr.pay.scim.server.service;

import fr.pay.scim.server.service.entity.group.Group;

public interface GroupService {

    public Group findGroupByDisplayName(String displayName);

    public Group createGroup(Group group);

    public Group findGroup(String id);

    public Group updateGroup(Group group);

    public void delete(String id);

}
