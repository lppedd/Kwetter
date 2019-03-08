package service;

import dao.UserDao;
import models.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class UserService {
    @Inject
    private UserDao dao;

    public UserService() {
        super();
    }

    public User create(User user) {
        if(dao.getByName(user.getName()) == null) {
            return dao.create(user);
        }
        return null;
    }

    public void delete(int id) {
        User user = dao.getById(id);
        dao.delete(user);
    }

    public void update(User user) {
        User u = dao.getById(user.getId());
        dao.update(u);
    }

    public User getById(int id) {
        return dao.getById(id);
    }

    public User getByName(String name) {
        return dao.getByName(name);
    }

    public List<User> getAll() {
        return dao.getAll();
    }

}
