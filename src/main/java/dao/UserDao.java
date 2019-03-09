package dao;

import exception.UserException;
import models.User;

import java.util.List;

public interface UserDao {
    User getById(int id);

    User getByName(String name);

    User getByEmail(String email);

    List<User> getAll();

    User create(User user) throws UserException;

    User update(User user);

    User updateUserType(User user);

    void delete(User user);

    void addFollower(User user, User follower);
}
