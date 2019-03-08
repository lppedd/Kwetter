package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tweet")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;
    private Date date;
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="tweet_like",
            joinColumns = @JoinColumn(name="tweet_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private List<User> likes;

    public Tweet() {

    }

    public Tweet(String content, User user) {
        this.content = content;
        this.date = new Date();
        this.user = user;
        this.likes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return this.content;
    }

    public Date getDate() {
        return this.date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void addLike(User user) {
        this.likes.add(user);
    }

    public void removeLike(User user) {
        this.likes.remove(user);
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }
}
