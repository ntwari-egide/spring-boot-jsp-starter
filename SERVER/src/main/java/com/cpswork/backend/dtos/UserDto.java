package com.cpswork.backend.dtos;

public class UserDto {
    private String firstName;

    private String secondName;

    private String username;

    private String email;

    private String profilePicturePath;

    //    @JsonIgnore
    private String password;

    public UserDto() {
    }

    public UserDto(String firstName, String secondName, String username, String email, String profilePicturePath, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
        this.email = email;
        this.profilePicturePath = profilePicturePath;
        this.password = password;
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
}
