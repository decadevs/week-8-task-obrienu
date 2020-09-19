package com.obrien.picesmart.utils.builders;

import com.obrien.picesmart.model.Brand;
import com.obrien.picesmart.utils.DTO.BrandSendingDTO;

public class BrandSenderBuilder {
    private String brandName;
    private String mobile;
    private String email;
    private String website;
    private String address;
    private String about;
    private long id;

    public BrandSenderBuilder() {

    }

    public BrandSenderBuilder setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public BrandSenderBuilder setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public BrandSenderBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public BrandSenderBuilder setWebsite(String website) {
        this.website = website;
        return this;
    }

    public BrandSenderBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public BrandSenderBuilder setAbout(String about) {
        this.about = about;
        return this;
    }

    public BrandSenderBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public BrandSenderBuilder setBrand(Brand brand) {
        this.id = brand.getId();
        this.about = brand.getAbout();
        this.address = brand.getAddress();
        this.brandName = brand.getBrandName();
        this.email = brand.getEmail();
        this.website = brand.getWebsite();
        this.mobile = brand.getMobile();
        return this;
    }

    public BrandSendingDTO build(){
        return new BrandSendingDTO( id,  brandName,  mobile,  email,  website,  address,  about);
    }

}
