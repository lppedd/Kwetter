package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String location;
    private String web;
    private String bio;
    private UserType userType;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="follow",
            joinColumns = @JoinColumn(name="followed_id"),
            inverseJoinColumns = @JoinColumn(name="follower_id")
    )
    private List<User> followers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="follow",
            joinColumns = @JoinColumn(name="follower_id"),
            inverseJoinColumns = @JoinColumn(name="followed_id")
    )
    private List<User> following;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="user_tweet",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="tweet_id")
    )
    private List<Tweet> tweets;

    public User() {

    }

    public User(String name, String location, String web, String bio, UserType userType) {
        this.name = name;
        this.location = location;
        this.web = web;
        this.bio = bio;
        this.userType = userType;
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.tweets = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void addFollower(User user) {
        this.followers.add(user);
    }

    public void addFollowing(User user) {
        this.following.add(user);
    }

    public void removeFollower(User user) {
        this.followers.remove(user);
    }

    public void removeFollowing(User user) {
        this.following.remove(user);
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public void addTweet(Tweet tweet) {
        this.tweets.add(tweet);
    }

    public void removeTweet(Tweet tweet) {
        this.tweets.remove(tweet);
    }
}
