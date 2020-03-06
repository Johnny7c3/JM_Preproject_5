package dao;

import model.User;

import java.util.List;

public interface UserDao {
    boolean addUser(User user);
    List<User> getAllUser();
    boolean userExist(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
    void updateUser(User updateUser);
    void deleteUser(Long id);
    void deleteAllUsers();
}
