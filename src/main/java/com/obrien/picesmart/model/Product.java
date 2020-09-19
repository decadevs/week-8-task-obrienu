package com.obrien.picesmart.model;



import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Entity(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Column(columnDefinition = "longtext")
    private String description;
    @Column(name = "image_url")
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Review> review;
    private LocalDateTime timestamp = LocalDateTime.now();
    @Column(name = "avg_rating")
    private double avgRating;
    @Column(name = "total_rating")
    private int totalRating;
    private String catchPhrase;
    private int price;
    private String category;

    public Product() {
    }


    public Product(long id, String name, String description, String imageUrl, Brand brand, Set<Review> review, LocalDateTime timestamp, double avgRating, int totalRating, String catchPhrase, int price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.brand = brand;
        this.review = review;
        this.timestamp = timestamp;
        this.avgRating = avgRating;
        this.totalRating = totalRating;
        this.catchPhrase = catchPhrase;
        this.price = price;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<Review> getReview() {
        return review;
    }

    public void setReview(Set<Review> review) {
        this.review = review;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
