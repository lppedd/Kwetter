package dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.jetbrains.annotations.NotNull;

import dao.TweetDao;
import models.Tweet;
import models.User;

@Singleton
public class TweetDaoMemory implements TweetDao {
    private final AtomicInteger idIncrement = new AtomicInteger(0);
    private final List<Tweet> tweets = new CopyOnWriteArrayList<>();

    @NotNull
    @Override
    public Optional<Tweet> findById(final int id) {
        return tweets.stream()
                     .filter(byId(id))
                     .findFirst();
    }

    @NotNull
    @Override
    public List<Tweet> findByUser(final int id) {
        return tweets.stream()
                     .filter(tweet -> {
                         final User user = tweet.getUser();
                         return user != null && user.getId() == id;
                     }).collect(Collectors.toList());
    }

    @NotNull
    @Override
    public List<Tweet> getAll() {
        // Defensive copy to avoid state mutation
        return new ArrayList<>(tweets);
    }

    @NotNull
    @Override
    public List<Tweet> findByContent(final String content) {
        return tweets.stream()
                     .filter(tweet -> {
                         final String tc = tweet.getContent();
                         return tc != null && tc.contains(content);
                     }).collect(Collectors.toList());
    }

    @Override
    public Tweet create(final Tweet tweet) {
        final String content = tweet.getContent();

        if (content != null && !content.isEmpty()) {
            if (content.length() < 141) {
                tweet.setId(getIncrementId());
                tweets.add(tweet);
                return tweet;
            }
        }

        return null;
    }

    @Override
    public Tweet update(final Tweet tweet) {
        for (Tweet kweet : tweets) {
            if (kweet.getId() == tweet.getId()) {
                // FIXME invalid!
                kweet = tweet;
            }
        }

        return null;
    }

    @Override
    public void delete(final Tweet tweet) {
        tweets.remove(tweet);
    }

    private int getIncrementId() {
        return idIncrement.incrementAndGet();
    }

    private static Predicate<Tweet> byId(final int id) {
        return tweet -> tweet.getId() == id;
    }
}
