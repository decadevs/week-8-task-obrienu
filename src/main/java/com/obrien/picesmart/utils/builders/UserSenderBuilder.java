package com.obrien.picesmart.utils.builders;

import com.obrien.picesmart.model.User;
import com.obrien.picesmart.utils.DTO.UserSendingDTO;

public class UserSenderBuilder {
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private long id;

    public UserSenderBuilder() {
    }

    public UserSenderBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserSenderBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserSenderBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserSenderBuilder setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public UserSenderBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public UserSenderBuilder setUser(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.mobile = user.getMobile();
        return this;
    }

    public UserSendingDTO build(){
        return new UserSendingDTO( id,  firstName,  lastName,  email,  mobile);
    }
}
