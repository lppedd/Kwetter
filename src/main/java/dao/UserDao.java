package dao;

import models.User;

import java.util.List;

public interface UserDao {
    User getById(int id);

    User getByName(String name);

    List<User> getAll();

    User create(User user);

    User update(User user);

    void delete(User user);

    void addFollower(User user, User follower);
}
