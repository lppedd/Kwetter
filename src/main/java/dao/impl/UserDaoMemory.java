package dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import javax.inject.Singleton;

import org.jetbrains.annotations.NotNull;

import dao.UserDao;
import exception.UserException;
import models.User;

// FIXME it was declared as RequestScoped. That means each request would have
//  had a totally new UserDao. That's the problem you had with initialization
@Singleton
public class UserDaoMemory implements UserDao {
    // Use AtomicInteger as a counter. It's the same as "volatile int".
    // For multi-threaded environments
    private final AtomicInteger idIncrement = new AtomicInteger(0);

    // CopyOnWriteArrayList for a multi-threaded environment.
    // Still this isn't an optimal solution, but an acceptable one
    private final List<User> users = new CopyOnWriteArrayList<>();

    @NotNull
    @Override
    public Optional<User> getById(final int id) {
        // Stream version of a User lookup + Optional to avoid NPEs.
        // A lot cleaner imho
        return users.stream()
                    .filter(byId(id))
                    .findFirst();
    }

    @NotNull
    @Override
    public Optional<User> getByName(final String name) {
        return users.stream()
                    .filter(byName(name))
                    .findFirst();
    }

    @NotNull
    @Override
    public Optional<User> getByEmail(final String email) {
        return users.stream()
                    .filter(byEmail(email))
                    .findFirst();
    }

    @NotNull
    @Override
    public List<User> getAll() {
        // Defensive copying. Good approach to maintain immutability of the
        // internal state. A user might still be modified, but the internal
        // list cannot be resized
        return new ArrayList<>(users);
    }

    @Override
    public User create(final User user) throws UserException {
        validateCreate(user);
        user.setId(getIncrementId());
        users.add(user);
        return user;
    }

    @Override
    public User update(final User user) {
        for (final User u : users) {
            if (user.getId() == u.getId()) {
                // FIXME what if another thread holds a reference to this object?
                //  changes won't be seen (and might never be)
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
    public User updateUserType(final User user) {
        for (final User u : users) {
            if (u.getId() == user.getId()) {
                // FIXME same problem as above. Multi-threading
                u.setUserType(user.getUserType());
                return user;
            }
        }

        return null;
    }

    @Override
    public void delete(final User user) {
        users.remove(user);
    }

    @Override
    public void addFollower(
            final User user,
            final User follower) {
        final int indexOfUser = users.indexOf(user);
        final int indexOfFollower = users.indexOf(follower);

        // Optional to avoid NPEs
        Optional.ofNullable(users.get(indexOfUser))
                .ifPresent(u -> u.addFollower(follower));

        Optional.ofNullable(users.get(indexOfFollower))
                .ifPresent(u -> u.addFollowing(user));
    }

    private void validateCreate(final User user) throws UserException {
        // Check nulls!
        Objects.requireNonNull(user, "User cannot be null");
        Objects.requireNonNull(user.getName(), "Name cannot be null");

        final int length = user.getName().length();

        if (length > 20 || length < 1) {
            throw new UserException("Username has an invalid length");
        }
    }

    private int getIncrementId() {
        return idIncrement.incrementAndGet();
    }

    private static Predicate<User> byId(final int id) {
        return user -> user.getId() == id;
    }

    private static Predicate<User> byName(final String name) {
        return user -> Objects.equals(user.getName(), name);
    }

    private static Predicate<User> byEmail(final String email) {
        return user -> Objects.equals(user.getEmail(), email);
    }
}
