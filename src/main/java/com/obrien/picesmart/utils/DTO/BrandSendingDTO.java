package com.obrien.picesmart.utils.DTO;


public class BrandSendingDTO {
    private long id;
    private String brandName;
    private String mobile;
    private String email;
    private String website;
    private String address;
    private String about;

    public BrandSendingDTO() {
    }

    public BrandSendingDTO(long id, String brandName, String mobile, String email, String website, String address, String about) {
        this.id = id;
        this.brandName = brandName;
        this.mobile = mobile;
        this.email = email;
        this.website = website;
        this.address = address;
        this.about = about;
    }

    public long getId() {
        return id;
    }

    public void setId(long brand_id) {
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
