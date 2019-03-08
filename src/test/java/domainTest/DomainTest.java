package domainTest;

import models.Tweet;
import models.User;
import models.UserType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DomainTest {
    List<User> users = new ArrayList<>();

    @Before
    public void setUp() { // populate testset so it is representative

        for (int i = 0; i < 10; i++) {
            User user = new User("User" + i, "Location" + i, "website" + i, "bio" + i, UserType.REGULAR);
            users.add(user);
        }

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            for (int j = 0; j < users.size(); j++) {
                User usr = users.get(j);
                Tweet tweet = new Tweet("content" + j, user);

                if (i != j) {
                    user.addTweet(tweet);
                    user.addFollower(usr);
                    user.addFollowing(usr);
                }
            }

        }


    }

    @Test
    public void userTest() {
        // Test object creation
        User mike = new User("Mike", "Helmond", "http://mike.nl", "Just living life", UserType.REGULAR);
        User pim = new User("Pim", "Eindhoven", "http://pim.nl", "Just living life", UserType.REGULAR);

        // Testing populating of users list (10 are populated before each test run)
        users.add(mike);
        assertEquals(11, users.size());

        // Add follower
        mike.addFollower(pim);
        assertEquals(mike.getFollowers().size(), 1);
        assertEquals(mike.getFollowers().get(0).getName(), "Pim");

        // Remove follower
        mike.removeFollower(pim);
        assertEquals(mike.getFollowers().size(), 0);

        // Add following
        mike.addFollowing(pim);
        assertEquals(mike.getFollowing().size(), 1);
        assertEquals(mike.getFollowing().get(0).getName(), "Pim");

        // Remove following
        mike.removeFollowing(pim);
        assertEquals(mike.getFollowing().size(), 0);

    }

    @Test
    public void TweetTest() {
        // Test object creation
        User mike = new User("Mike", "Helmond", "http://mike.nl", "Just living life", UserType.REGULAR);
        Tweet tweet = new Tweet("Test", mike);

        // Test amount of tweets initialized
        User user = users.get(0);
        assertEquals(9, user.getTweets().size());

        // Add tweet
        mike.addTweet(tweet);
        assertEquals(1, mike.getTweets().size());
        assertEquals(mike.getTweets().get(0).getContent(), "Test");

        // Remove tweet
        mike.removeTweet(tweet);
        assertEquals(0, mike.getTweets().size());

    }
}
