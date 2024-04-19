package fr.pay.scim.server.service.implementation.memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.pay.scim.server.service.GroupService;
import fr.pay.scim.server.service.entity.group.Group;
import fr.pay.scim.server.service.entity.group.Groups;

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

    @Override
    public Groups findGroups(String filter, String attributes, String excludedAttributes, String sortBy,
            String sortOrder, int startIndex, int count) {

        List<Group> groupsInSearch = groupsInMemory;

        if (filter != null) {

            Pattern patternDisplayName = Pattern.compile("^displayName eq \"(.*)\"$", Pattern.CASE_INSENSITIVE);
            Matcher matcherDisplayName = patternDisplayName.matcher(filter);
            
            if (matcherDisplayName.find()) {
				String displayName = matcherDisplayName.group(1);

                groupsInSearch = groupsInSearch.stream()
                                    .filter(u -> u.getDisplayName().equalsIgnoreCase(displayName))
                                    .collect(Collectors.toList());
            }
        }

        // On ne transmet pas les membres lors de la recherhce
        // TODO : A faire

        Groups groups = new Groups();

        groups.setTotalResults(groupsInSearch.size());
        
        groups.setStartIndex(startIndex);
        groups.setItemsPerPage(count);
        
        groups.setGroups(groupsInSearch.stream()
                                        .skip(startIndex-1)
                                        .limit(count)
                                        .collect(Collectors.toList()));
        
        return groups;
    }

     
}
