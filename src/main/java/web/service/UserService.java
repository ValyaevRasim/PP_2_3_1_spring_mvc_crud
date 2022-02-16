package web.service;

import web.entity.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);
    public void deleteUser(User user);
    public void deleteUserById(long id);
    public void updateUser(long id, User user);
    public List<User> getAllUsers();
    public User getUserById (long id);
}
