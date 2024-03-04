package com.springboot.springbootref.cucumber.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("Id")
    private String Id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("email")
    private String email;

    @JsonProperty("profile")
    private String profile;

    @JsonProperty("csvData")
    private String csvData;

    @JsonProperty("verified")
    private String verified;

    public User() {}

    public User(String Id,String username, String password, String phoneNumber, String email, String profile, String csvData, String verified) {
        this.Id = Id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profile = profile;
        this.csvData = csvData;
        this.verified = verified;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getCsvData() {
        return csvData;
    }

    public void setCsvData(String csvData) {
        this.csvData = csvData;
    }

    public String isVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }
}
