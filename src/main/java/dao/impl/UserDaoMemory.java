package dao.impl;

import dao.UserDao;
import models.User;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class UserDaoMemory implements UserDao {
    private List<User> users = new ArrayList<>();
    private int idIncrement = 0;

    @Override
    public User getById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User create(User user) {
        user.setId(getIncrementId());
        users.add(user);
        return user;
    }

    @Override
    public User update(User user) {
        for (User u : users) {
            if (user.getId() == u.getId()) {
                u.setName(user.getName());
                u.setWeb(user.getWeb());
                u.setBio(user.getBio());
                u.setLocation(user.getLocation());

                u.setFollowers(user.getFollowers());
                u.setFollowing(user.getFollowing());
                return user;
            }
        }
        return null;
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public void addFollower(User user, User follower) {
        int indexOf = this.users.indexOf(user);
        users.get(indexOf).addFollower(follower);

    }


    private int getIncrementId() {
        idIncrement++;
        return idIncrement;
    }
}
