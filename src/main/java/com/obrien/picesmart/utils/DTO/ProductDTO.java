package com.obrien.picesmart.utils.DTO;

import com.obrien.picesmart.model.Brand;


public class ProductDTO {
    private long id;
    private String name;
    private String description;
    private String imageUrl;
    private Brand brand;
    private int totalReviews = 0;
    private double avgRating = 0;
    private int totalRating = 0;
    private String catchPhrase;
    private int price;
    private String category;

    public ProductDTO() {
    }

    public ProductDTO(long id, String name, String description, String imageUrl, Brand brand, int totalReviews, double avgRating, int totalRating, String catchPhrase, int price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.brand = brand;
        this.totalReviews = totalReviews;
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

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
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
