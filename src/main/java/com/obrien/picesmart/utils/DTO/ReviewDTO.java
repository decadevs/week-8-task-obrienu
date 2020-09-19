package com.obrien.picesmart.utils.DTO;

import com.obrien.picesmart.model.Product;
import com.obrien.picesmart.model.User;

import java.time.LocalDateTime;


public class ReviewDTO {
    private long id;
    private String text;
    private UserSendingDTO user;
    private Product product;
    private int rating;
    private String subject;
    LocalDateTime  timestamp = LocalDateTime.now();

    public ReviewDTO() {
    }

    public ReviewDTO(long id, String text, UserSendingDTO user, Product product, int rating, String subject, LocalDateTime timestamp) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.product = product;
        this.rating = rating;
        this.subject = subject;
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

    public UserSendingDTO getUser() {
        return user;
    }

    public void setUser(UserSendingDTO user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setRating(String rating) {
        try{
            this.rating = Integer.parseInt(rating);
        }catch (NumberFormatException e){
            this.rating = 0;
        }

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
        setTimestamp();
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        this.timestamp = LocalDateTime.now();
    }

    public void setTimestamp(LocalDateTime localDateTime) {
        this.timestamp = localDateTime;
    }
}
