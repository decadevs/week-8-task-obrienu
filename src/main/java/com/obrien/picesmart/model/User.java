package com.obrien.picesmart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    private String email;
    private String hash;
    private String mobile;
    private LocalDateTime timestamp = LocalDateTime.now();

    public User() {
    }

    public User(long id, String firstName, String lastName, String email, String hash, String mobile) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hash = hash;
        this.mobile = mobile;
    }

    public User(String firstName, String lastName, String email, String hash, String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hash = hash;
        this.mobile = mobile;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", hash='" + hash + '\'' +
                ", mobile='" + mobile + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
