package com.cpswork.backend.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID id;

    private String firstName;

    private String secondName;

    private String username;

    private String email;

    private String profilePicturePath;

//    @JsonIgnore
    private String password;

    public User(){}

    public User(String firstName, String secondName, String username, String email, String profilePicturePath, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
        this.email = email;
        this.profilePicturePath = profilePicturePath;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", profilePicturePath='" + profilePicturePath + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}