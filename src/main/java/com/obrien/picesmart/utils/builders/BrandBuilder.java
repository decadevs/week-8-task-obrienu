package com.obrien.picesmart.utils.builders;

import com.obrien.picesmart.model.Brand;
import com.obrien.picesmart.utils.DTO.BrandReceivingDTO;

public class BrandBuilder {

    private String brandName;
    private String mobile;
    private String hash;
    private String email;
    private String website;
    private String address;
    private String about;

    public BrandBuilder() {
    }


    public BrandBuilder setBrand_name(String brand_name) {
        this.brandName = brand_name;
        return this;
    }

    public BrandBuilder setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public BrandBuilder setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public BrandBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public BrandBuilder setWebsite(String website) {
        this.website = website;
        return this;
    }

    public BrandBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public BrandBuilder setAbout(String about) {
        this.about = about;
        return this;
    }

    public BrandBuilder setBrand(BrandReceivingDTO brandReceivingDTO) {
       this.about = brandReceivingDTO.getAbout();
       this.address = brandReceivingDTO.getAddress();
       this.brandName = brandReceivingDTO.getBrandName();
       this.email  = brandReceivingDTO.getEmail();
       this.mobile = brandReceivingDTO.getMobile();
       this.website = brandReceivingDTO.getWebsite();
        return this;
    }

    public Brand build(){
        return new Brand( brandName, mobile,  hash,  email,  website,  address,  about);
    }

}
