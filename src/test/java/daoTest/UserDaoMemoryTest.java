package daoTest;

import dao.impl.UserDaoMemory;
import models.User;
import models.UserType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserDaoMemoryTest {
    private UserDaoMemory dao = new UserDaoMemory();

    @Test
    public void getAllTest() {
        // String name, String location, String web, String bio, UserType userType)
        User mike = new User("Mike", "Helmond", "http://mike.nl", "Just living life", UserType.REGULAR);
        User pim = new User("Pim", "Eindhoven", "http://pim.nl", "Just living life", UserType.REGULAR);

        List<User> users = new ArrayList<>();
        users.add(mike);
        users.add(pim);

        dao.create(mike);
        dao.create(pim);

        List<User> daoUsers = dao.getAll();

        assertEquals(users.size(), daoUsers.size()); // test equal amount of users
        assertEquals(true, daoUsers.containsAll(users)); // test if list really contains created users
    }

    @Test
    public void getByNameTest() {
        User mike = dao.create(new User("Mike", "Helmond", "http://mike.nl", "Just living life", UserType.REGULAR));
        User found = dao.getByName("Mike");
        assertEquals(mike, found); // test existing name

        User notFound = dao.getByName("Bestaat niet"); // test not existing name
        assertNull(notFound);
    }

    @Test
    public void getByIdTest() {
        User mike = dao.create(new User("Mike", "Helmond", "http://mike.nl", "Just living life", UserType.REGULAR));
        User found = dao.getById(mike.getId());
        assertEquals(mike, found); // test existing name

        User notFound = dao.getById(mike.getId() + 1234); // test not existing name
        assertNull(notFound);
    }

    @Test
    public void deleteTest() {
        User mike = dao.create(new User("Mike", "Helmond", "http://mike.nl", "Just living life", UserType.REGULAR));
        dao.delete(mike);

        User found = dao.getById(mike.getId());
        assertNull(found); // test delete of existing user
    }
}
