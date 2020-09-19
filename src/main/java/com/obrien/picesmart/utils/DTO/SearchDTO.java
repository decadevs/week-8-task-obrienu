package com.obrien.picesmart.utils.DTO;

public class SearchDTO {
    private String query;
    private String rating = "desc";
    private String price = "desc";

    public SearchDTO() {
    }

    public SearchDTO(String query, String rating, String price) {
        this.query = query;
        this.rating = rating;
        this.price = price;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
