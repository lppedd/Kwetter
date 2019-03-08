package dao;

import models.Tweet;

import java.util.List;

public interface TweetDao {
    Tweet findById(int id);

    List<Tweet> findByUser(int id);

    List<Tweet> getAll();

    Tweet create(Tweet tweet);

    void delete(Tweet tweet);
}
