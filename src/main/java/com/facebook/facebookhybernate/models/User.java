package com.facebook.facebookhybernate.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Entity(name="users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String birthdate;
    private String email;
    private String firstname;
    private String lastname;
    private String mobile;
    @Column(name = "hash")
    private String password;
    private String sex;
    private String about;
    private LocalDateTime timestamp = LocalDateTime.now();


    public User() {
    }

    public User(long id, String birthdate, String email, String firstname, String lastname, String mobile, String password, String sex, String about, LocalDateTime timestamp) {
        this.id = id;
        this.birthdate = birthdate;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.password = password;
        this.sex = sex;
        this.about = about;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getName(){
        return firstname + " " + lastname;
    }

    public void setBirthdate(String birthdate) {
        if(birthdate.contains("/")) {
            String[] dates = birthdate.split("/");
            this.birthdate = dates[2] + "-" + dates[1] + "-" + dates[0];
        }else
            this.birthdate = birthdate;

    }
}
