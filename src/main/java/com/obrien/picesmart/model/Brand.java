package com.obrien.picesmart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity(name="brands")
public class Brand {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "brand_name")
    private String brandName;
    private String mobile;
    private String hash;
    private String email;
    private String website;
    @Column(columnDefinition = "longtext")
    private String address;
    @Column(columnDefinition = "longtext")
    private String about;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Brand() {
    }

    public Brand(String brand_name, String mobile, String hash, String email, String website, String address, String about) {

        this.brandName = brand_name;
        this.mobile = mobile;
        this.hash = hash;
        this.email = email;
        this.website = website;
        this.address = address;
        this.about = about;
    }

    public Brand(long id, String brand_name, String mobile, String hash, String email, String website, String address, String about) {
        this.id = id;
        this.brandName = brand_name;
        this.mobile = mobile;
        this.hash = hash;
        this.email = email;
        this.website = website;
        this.address = address;
        this.about = about;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brand_name) {
        this.brandName = brand_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

}
