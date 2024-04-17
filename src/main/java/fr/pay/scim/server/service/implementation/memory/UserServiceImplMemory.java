package fr.pay.scim.server.service.implementation.memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.pay.scim.server.service.UserService;
import fr.pay.scim.server.service.entity.user.User;

@Service
public class UserServiceImplMemory implements UserService {

    private List<User> users = new ArrayList<>();

     @Override
    public User createUser(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setCreated(new Date());
        user.setLastModified(new Date());
        users.add(user);
        return user;
    }

    @Override
    public User findUser(String id) {
        return users.stream().filter(u -> id.equals(u.getId())).findFirst().orElse(null);
    }

    @Override
    public User findUserByUserName(String userName) {
        return users.stream().filter(u -> userName.equalsIgnoreCase(u.getUserName())).findFirst().orElse(null);
    }

    @Override
    public User updateUser(User user) {

        delete(user.getId());

        user.setLastModified(new Date());
        users.add(user);

        return user;
    }

    @Override
    public void delete(String id) {
        User old = users.stream().filter(u -> id.equals(u.getId())).findFirst().orElse(null);
        users.remove(old);
    }


}
