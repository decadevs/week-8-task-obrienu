package com.obrien.picesmart.utils.DTO;

public class SignInReceivingDTO {
    private String details;
    private String password;

    public SignInReceivingDTO() {
    }

    public SignInReceivingDTO(String details, String password) {
        this.details = details;
        this.password = password;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
