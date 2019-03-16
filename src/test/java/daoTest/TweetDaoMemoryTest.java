package daoTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import dao.impl.TweetDaoMemory;
import dao.impl.UserDaoMemory;
import models.Tweet;
import models.User;
import models.UserType;

public class TweetDaoMemoryTest {
    private TweetDaoMemory dao = new TweetDaoMemory();
    private UserDaoMemory userDao = new UserDaoMemory();

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User("Mike", "mike@live.nl", "Helmond", "http://mike.nl", "Just living life", UserType.REGULAR);
        userDao.create(user);
    }

    @Test
    public void createTest() {
        // Case 1 - Create tweet with content
        Tweet tweet = dao.create(new Tweet("Tweet message", user));
        assertNotNull(tweet);

        // Case 2 - Check if content can be empty
        Tweet tweetEmpty = dao.create(new Tweet("", user));
        assertNull(tweetEmpty);

        // Case 3 - Check if content can be 140 Characters
        String message140Char = "Hello World! Hello World! Hello World! Hello "
                + "World! Hello World! Hello World! Hello World! Hello World! "
                + "Hello World! Hello World! Hello Worl"; //140 characters
        Tweet tweet140Chars = dao.create(new Tweet(message140Char, user));
        assertNotNull(tweet140Chars);

        // Case 4 - Check if message can be 141 Characters
        String message141Char = "Hello World! Hello World! Hello World! Hello "
                + "World! Hello World! Hello World! Hello World! Hello World! "
                + "Hello World! Hello World! Hello World"; //141 characters
        Tweet tweet141Chars = dao.create(new Tweet(message141Char, user));
        assertNull(tweet141Chars);
    }

    @Test
    public void findByIdTest() {
        //Case 1 - Find an existing tweet by id
        final Tweet tweet = new Tweet("Hello world!", user);
        final Tweet createdTweet = dao.create(tweet);

        final Optional<Tweet> tweetFound = dao.findById(createdTweet.getId());
        assertTrue(tweetFound.isPresent());
        assertEquals(createdTweet, tweetFound.get());

        //Case 2 - Find a not existing tweet by id
        final Optional<Tweet> tweet2Found = dao.findById(createdTweet.getId() + 1);
        assertFalse(tweet2Found.isPresent());
    }

    @Test
    public void findByContentTest() {
        //Case 1 - Find an existing tweet by message
        Tweet tweet = new Tweet("Hello world!", user);
        Tweet createdTweet = dao.create(tweet);

        List<Tweet> tweetFound = dao.findByContent("Hello world!");
        assertEquals(1, tweetFound.size());

        //Casee 2 - Find a not existing tweet by message
        Tweet tweet2 = new Tweet("Hello world!", user);
        dao.create(tweet2);

        List<Tweet> tweet2Found = dao.findByContent("!dlorw elloh");
        List<Tweet> emptyList = new ArrayList<>();

        assertEquals(emptyList, tweet2Found);
    }

    @Test
    public void findByUserTest() {
        //Case 1 - Find all existing tweets by user
        Tweet tweet1 = new Tweet("Hello world!", user);
        Tweet tweet2 = new Tweet("Hello world!", user);

        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet1);
        tweets.add(tweet2);

        dao.create(tweet1);
        dao.create(tweet2);

        List<Tweet> tweetsService = dao.findByUser(user.getId());

        assertEquals(tweets.size(), tweetsService.size());
    }

    @Test
    public void deleteTest() throws Exception {
        //Case 1 - Delete an existing Tweet
        Tweet tweet = new Tweet("Hello world!", user);
        Tweet createdTweet = dao.create(tweet);

        dao.delete(tweet);

        assertNull(dao.findById(tweet.getId()));
    }
}
