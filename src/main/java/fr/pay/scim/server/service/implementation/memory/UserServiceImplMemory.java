package fr.pay.scim.server.service.implementation.memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pay.scim.server.service.UserService;
import fr.pay.scim.server.service.entity.user.User;
import fr.pay.scim.server.service.entity.user.Users;

@Service
public class UserServiceImplMemory implements UserService {

    @Autowired
    GroupServiceImplMemory groupServiceImplMemory;

    private List<User> usersInMemory = new ArrayList<>();

     @Override
    public User createUser(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setCreated(new Date());
        user.setLastModified(new Date());
        usersInMemory.add(user);
        return user;
    }

    @Override
    public User findUser(String id) {
        return usersInMemory.stream().filter(u -> id.equals(u.getId())).findFirst().orElse(null);
    }

    @Override
    public User findUserByUserName(String userName) {
        return usersInMemory.stream().filter(u -> userName.equalsIgnoreCase(u.getUserName())).findFirst().orElse(null);
    }
    
    @Override
    public User updateUser(User user) {

        groupServiceImplMemory.userDeleted(user);
        
        delete(user.getId());

        user.setLastModified(new Date());
        usersInMemory.add(user);

        return user;
    }

    @Override
    public void delete(String id) {
        User old = usersInMemory.stream().filter(u -> id.equals(u.getId())).findFirst().orElse(null);
        usersInMemory.remove(old);
    }


    @Override
    public Users findUsers(String filter, String attributes, String excludedAttributes, String sortBy, String sortOrder, int startIndex, int count) {

        List<User> usersInSearch = usersInMemory;

        if (filter != null) {

            Pattern patternUsername = Pattern.compile("^username eq \"(.*)\"$", Pattern.CASE_INSENSITIVE);
            Matcher matcherUsername = patternUsername.matcher(filter);
            
            if (matcherUsername.find()) {
				String username = matcherUsername.group(1);

                usersInSearch = usersInSearch.stream()
                                    .filter(u -> u.getUserName().equalsIgnoreCase(username))
                                    .collect(Collectors.toList());
            }
        }

        Users users = new Users();

        users.setTotalResults(usersInSearch.size());

        users.setStartIndex(startIndex);
        users.setItemsPerPage(count);

        users.setUsers(usersInSearch.stream()
                                    .skip(startIndex-1)
                                    .limit(count)
                                    .collect(Collectors.toList()));

        return users;
    }


}
