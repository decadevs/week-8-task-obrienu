package com.facebook.facebookhybernate.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "post_likes")
public class PostLike implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    public PostLike() {
    }

    public PostLike(long id, User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
