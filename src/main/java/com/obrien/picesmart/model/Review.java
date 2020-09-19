package com.obrien.picesmart.model;



import javax.persistence.*;
import java.time.LocalDateTime;


@Entity(name="review")
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(columnDefinition = "longtext")
    private String text;
    private String subject;
    private int rating;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Review() {
    }

    public Review(long id, String text, String subject, int rating, User user, Product product, LocalDateTime timestamp) {
        this.id = id;
        this.text = text;
        this.subject = subject;
        this.rating = rating;
        this.user = user;
        this.product = product;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
