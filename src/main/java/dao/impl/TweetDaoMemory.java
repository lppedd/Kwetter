package dao.impl;

import dao.TweetDao;
import models.Tweet;
import models.User;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class TweetDaoMemory implements TweetDao {
    private List<Tweet> tweets = new ArrayList<>();
    private int idIncrement = 0;

    @Override
    public Tweet findById(int id) {
        for(Tweet tweet : tweets) {
            if(tweet.getId() == id) {
                return tweet;
            }
        }
        return null;
    }

    @Override
    public List<Tweet> findByUser(int id) {
        List<Tweet> tweetsFound = new ArrayList<>();

        for(Tweet tweet : tweets) {
            if(tweet.getUser().getId() == id) {
                tweetsFound.add(tweet);
            }
        }
        return tweetsFound;
    }

    @Override
    public List<Tweet> getAll() {
        return tweets;
    }

    @Override
    public List<Tweet> findByContent(String content) {
        List<Tweet> tweetsFound = new ArrayList<>();

        for(Tweet tweet : tweets) {
            if(tweet.getContent().contains(content)) {
                tweetsFound.add(tweet);
            }
        }
        return tweetsFound;
    }

    @Override
    public Tweet create(Tweet tweet) {
        if(!tweet.getContent().isEmpty()) {
            if(tweet.getContent().length() < 141) {
                tweet.setId(getIncrementId());
                tweets.add(tweet);
                return tweet;
            }
        }
        return null;
    }

    @Override
    public Tweet update(Tweet tweet) {
        for(Tweet kweet : tweets) {
            if(kweet.getId() == tweet.getId()) {
                kweet = tweet;
            }
        }
        return null;
    }

    @Override
    public void delete(Tweet tweet) {
        tweets.remove(tweet);
    }

    private int getIncrementId() {
        idIncrement++;
        return idIncrement;
    }
}
