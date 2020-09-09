package com.facebook.facebookhybernate.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String post;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user ;
    private String created_at;
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PostLike> likes;
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Comment> comments;
    private LocalDateTime timestamp = LocalDateTime.now();
    private boolean liked ;

    public Post() {
    }

    public Post(long id, String post, User user, String created_at, Set<PostLike> likes, Set<Comment> comments, LocalDateTime timestamp) {
        this.id = id;
        this.post = post;
        this.user = user;
        this.created_at = created_at;
        this.likes = likes;
        this.comments = comments;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public Set<PostLike> getLikes() {
        return likes;
    }

    public void setLikes(Set<PostLike> likes) {
        this.likes = likes;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }


    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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
