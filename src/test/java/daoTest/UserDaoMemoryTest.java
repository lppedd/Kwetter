package daoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.Test;

import dao.UserDao;
import dao.impl.UserDaoMemory;
import models.User;
import models.UserType;

public class UserDaoMemoryTest {
    private final UserDao dao = new UserDaoMemory();

    @Test
    public void getAllTest() throws Exception {
        // String name, String location, String web, String bio, UserType userType)
        final User mike = new User("Mike", "mike@live.nl", "Helmond", "http://mike.nl", "Just living life", UserType.REGULAR);
        final User pim = new User("Pim", "pim@live.nl", "Eindhoven", "http://pim.nl", "Just living life", UserType.REGULAR);

        final Collection<User> users = new ArrayList<>();
        users.add(mike);
        users.add(pim);

        dao.create(mike);
        dao.create(pim);

        final Collection<User> daoUsers = dao.getAll();

        assertEquals(users.size(), daoUsers.size()); // test equal amount of users
        assertTrue(daoUsers.containsAll(users)); // test if list really contains created users
    }

    @Test
    public void getByNameTest() throws Exception {
        final User mike =
                dao.create(new User(
                        "Mike",
                        "mike@live.nl",
                        "Helmond",
                        "http://mike.nl",
                        "Just living life",
                        UserType.REGULAR)
                );

        final Optional<User> found = dao.getByName("Mike");

        assertTrue(found.isPresent());
        assertEquals(mike, found.get()); // test existing name

        final Optional<User> notFound = dao.getByName("Bestaat niet"); // test not existing name
        assertFalse(notFound.isPresent());
    }

    @Test
    public void getByIdTest() throws Exception {
        final User mike =
                dao.create(new User(
                        "Mike",
                        "mike@live.nl",
                        "Helmond",
                        "http://mike.nl",
                        "Just living life",
                        UserType.REGULAR)
                );

        final Optional<User> found = dao.getById(mike.getId());
        assertTrue(found.isPresent());
        assertEquals(mike, found.get()); // test existing name

        final Optional<User> notFound = dao.getById(mike.getId() + 1234); // test not existing name
        assertFalse(notFound.isPresent());
    }

    @Test
    public void deleteTest() throws Exception {
        final User mike =
                dao.create(new User(
                        "Mike",
                        "mike@live.nl",
                        "Helmond",
                        "http://mike.nl",
                        "Just living life",
                        UserType.REGULAR)
                );

        dao.delete(mike);

        final Optional<User> found = dao.getById(mike.getId());
        assertFalse(found.isPresent()); // test delete of existing user
    }

    @Test
    public void updateTest() throws Exception {
        final User paul =
                dao.create(new User(
                        "Paul",
                        "mike@live.nl",
                        "Friesland",
                        "http://paul.nl",
                        "Paulus",
                        UserType.REGULAR)
                );
        final User newPaul =
                new User(
                        "Paul",
                        "paul@live.nl",
                        "Friesland",
                        "http://paul.nl",
                        "Betere bio",
                        UserType.REGULAR
                );
        newPaul.setId(paul.getId());

        dao.update(newPaul);

        final Optional<User> updatedPaul = dao.getById(newPaul.getId());
        assertTrue(updatedPaul.isPresent());
        assertEquals(updatedPaul.get().getBio(), "Betere bio"); // test delete of existing user
    }
}
