package com.facebook.facebookhybernate.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String comment;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user ;
    private String created_at;
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
    @OneToMany(mappedBy = "comment", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CommentLike> likes;
    private LocalDateTime timestamp = LocalDateTime.now();
    private boolean liked;

    public Comment() {
    }

    public Comment(long id, String comment, User user, String created_at, Post post, Set<CommentLike> likes, LocalDateTime timestamp) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.created_at = created_at;
        this.post = post;
        this.likes = likes;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Set<CommentLike> getLikes() {
        return likes;
    }

    public void setLikes(Set<CommentLike> likes) {
        this.likes = likes;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
