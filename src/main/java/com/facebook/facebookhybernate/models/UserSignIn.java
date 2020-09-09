package com.facebook.facebookhybernate.models;

public class UserSignIn {
    private String email;
    private String password;

    public UserSignIn() {
    }

    public UserSignIn(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserSignIn{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
