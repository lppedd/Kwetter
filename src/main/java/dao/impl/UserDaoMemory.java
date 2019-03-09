package dao.impl;

import dao.UserDao;
import exception.UserException;
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
    public User getByEmail(String email) {
        for(User user : users) {
            if(user.getEmail().equals(email)) {
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
    public User create(User user) throws UserException {
        validateCreate(user);
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
    public User updateUserType(User user) {
        for(User u : users) {
            if(u.getId() == user.getId()) {
                u.setUserType(user.getUserType());
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
        int indexOfUser = this.users.indexOf(user);
        int indexOfFollower = this.users.indexOf(follower);

        users.get(indexOfUser).addFollower(follower);
        users.get(indexOfFollower).addFollowing(user);

    }

    private void validateCreate(User user) throws UserException {
        if(user.getName().length() > 20 || user.getName().length() < 0 || user.getName().isEmpty()) {
            throw new UserException("Username has an invalid length");
        }
    }

    private int getIncrementId() {
        idIncrement++;
        return idIncrement;
    }
}
