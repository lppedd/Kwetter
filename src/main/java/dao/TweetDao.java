package dao;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import models.Tweet;

public interface TweetDao {
    @NotNull
    Optional<Tweet> findById(final int id);

    @NotNull
    List<Tweet> findByUser(final int id);

    @NotNull
    List<Tweet> getAll();

    @NotNull
    List<Tweet> findByContent(final String content);

    @Nullable
    Tweet create(final Tweet tweet);

    @Nullable
    Tweet update(final Tweet tweet);

    void delete(final Tweet tweet);
}
