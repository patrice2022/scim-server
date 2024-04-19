package fr.pay.scim.server.service.implementation.memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.pay.scim.server.service.GroupService;
import fr.pay.scim.server.service.entity.group.Group;

@Service
public class GroupServiceImplMemory implements GroupService {

    private List<Group> groupsInMemory = new ArrayList<>();

    @Override
    public Group createGroup(Group group) {
        group.setId(UUID.randomUUID().toString());
        group.setCreated(new Date());
        group.setLastModified(new Date());
        groupsInMemory.add(group);
        return group;
    }

    @Override
    public Group findGroupByDisplayName(String displayName) {
        return groupsInMemory.stream().filter(u -> displayName.equalsIgnoreCase(u.getDisplayName())).findFirst().orElse(null);
    }

    @Override
    public Group findGroup(String id) {
        return groupsInMemory.stream().filter(u -> id.equals(u.getId())).findFirst().orElse(null);
    }

    @Override
    public Group updateGroup(Group group) {
        delete(group.getId());

        group.setLastModified(new Date());
        groupsInMemory.add(group);

        return group;

    }

    @Override
    public void delete(String id) {
        Group old = groupsInMemory.stream().filter(u -> id.equals(u.getId())).findFirst().orElse(null);
        groupsInMemory.remove(old);
    }

     
}