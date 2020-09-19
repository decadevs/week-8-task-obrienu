package com.obrien.picesmart.utils.builders;

import com.obrien.picesmart.model.User;
import com.obrien.picesmart.utils.DTO.UserReceivingDTO;

public class UserBuilder {

    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String hash;

    public UserBuilder() {
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public UserBuilder setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public UserBuilder setUser(UserReceivingDTO user) {
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.mobile = user.getMobile();
        return this;
    }

    public User build(){
            return  new User( firstName,  lastName,  email,  hash,  mobile);
    }


}
