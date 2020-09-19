package com.obrien.picesmart.utils.DTO;



public class BrandReceivingDTO {
    private long id;
    private String brandName;
    private String mobile;
    private String email;
    private String website;
    private String address;
    private String about;
    private String password;

    public BrandReceivingDTO() {
    }

    public BrandReceivingDTO(long id, String brand_name, String mobile, String email, String website, String address, String about, String password) {
        this.id = id;
        this.brandName = brand_name;
        this.mobile = mobile;
        this.email = email;
        this.website = website;
        this.address = address;
        this.about = about;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
