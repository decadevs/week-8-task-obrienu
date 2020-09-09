package com.facebook.facebookhybernate.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "comment_likes")
public class CommentLike implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="comment_id")
    private Comment comment;

    public CommentLike() {
    }

    public CommentLike(long id, User user, Comment comment) {
        this.id = id;
        this.user = user;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
