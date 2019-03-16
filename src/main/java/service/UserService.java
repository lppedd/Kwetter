package service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dao.UserDao;
import exception.UserException;
import models.User;

@Singleton
public class UserService {
    @NotNull private final UserDao dao;

    @Inject
    UserService(@NotNull final UserDao dao) {
        this.dao = dao;
    }

    @Nullable
    public User create(final User user) throws UserException {
        if (!dao.getByName(user.getName()).isPresent()) {
            return dao.create(user);
        }

        return null;
    }

    public void delete(final int id) {
        // Use functional style code as much as possible
        dao.getById(id).ifPresent(dao::delete);
    }

    public void update(final User user) {
        dao.getById(user.getId()).ifPresent(dao::update);
    }

    @NotNull
    public Optional<User> getById(final int id) {
        return dao.getById(id);
    }

    @NotNull
    public Optional<User> getByName(final String name) {
        return dao.getByName(name);
    }

    @NotNull
    public List<User> getAll() {
        return dao.getAll();
    }
}
