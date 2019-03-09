package serviceTest;

import dao.TweetDao;
import dao.UserDao;
import models.User;
import models.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.UserService;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
@Mock creates a mock.
@InjectMocks creates an instance of the class and injects the mocks that are created with the @Mock (or @Spy) annotations into this instance.
**/

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao; // this will be injected into userService

    @Test
    public void ServiceTest() {
        assertNotNull(userService);
    }

    @Before
    public void setUp() throws Exception {
//        User mike = new User("Mike", "mike@live.nl", "Helmond", "http://mike.nl", "Just living life", UserType.REGULAR);
//        User ccc = new User("c", "c", "Helmond", "http://mike.nl", "Just living life", UserType.REGULAR);
//        User added = userService.create(mike);
//        User added2 = userService.create(ccc);
//        List<User> users = userService.getAll();
    }

    @Test
    public void createTest1() throws Exception {
        // Case 1 - Existing Username
        User mike = new User("Mike", "mike@live.nl", "Helmond", "http://mike.nl", "Just living life", UserType.REGULAR);
        mike.setId(1);

        // specify behaviour
        when(userDao.getByName(mike.getName())).thenReturn(null);

        userService.create(mike);

        verify(userDao, times(1)).create(mike); // integration test
    }

    @Test
    public void createTest2() throws Exception {
        // Case 2 - Existing Email
        User mike = new User("Mike", "mike@live.nl", "Helmond", "http://mike.nl", "Just living life", UserType.REGULAR);
        mike.setId(1);

//        when(userDao.getByName(mike.getName())).thenReturn(null);
//        when(userDao.getByEmail(mike.getEmail())).thenReturn(mike);
        User added = userService.create(mike);
        List<User> users = userService.getAll();


        verify(userDao, never()).create(mike);
    }
}
