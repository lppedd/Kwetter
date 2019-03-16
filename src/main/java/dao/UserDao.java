package dao;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import exception.UserException;
import models.User;

public interface UserDao {
    @NotNull
    Optional<User> getById(final int id);

    @NotNull
    Optional<User> getByName(final String name);

    @NotNull
    Optional<User> getByEmail(final String email);

    @NotNull
    List<User> getAll();

    @Nullable
    User create(final User user) throws UserException;

    @Nullable
    User update(final User user);

    @Nullable
    User updateUserType(final User user);

    void delete(final User user);

    void addFollower(
            final User user,
            final User follower
    );
}
