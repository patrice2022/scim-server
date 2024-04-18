package fr.pay.scim.server.service;

import fr.pay.scim.server.service.entity.user.User;
import fr.pay.scim.server.service.entity.user.Users;

public interface UserService {

    public User createUser(User user);

    public User findUser(String id);
    public User findUserByUserName(String userName);

    public User updateUser(User user);

    public void delete(String id);

    public Users findUsers(String filter, String attributes, String excludedAttributes, String sortBy, String sortOrder, int startIndex, int count);
}
