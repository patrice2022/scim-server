package fr.pay.scim.server.service;

import fr.pay.scim.server.service.entity.user.User;

public interface UserService {

    public User createUser(User user);

    public User findUser(String id);
    public User findUserByUserName(String userName);

    public User updateUser(User user);

    public void delete(String id);
}
