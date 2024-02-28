package com_springboot_springbootref;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

import java.util.Objects;

@Entity
public class Register {
    @jakarta.persistence.Id
    @SequenceGenerator(
            name="user_id_sequence",
            sequenceName="user_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    private Integer Id;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String profile;
    private String csvData;
    public String verified;

    public Register() {}
    public Register(Integer Id,String username, String password, String phoneNumber, String email, String profile, String csvData, String verified) {
        this.Id = Id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profile = profile;
        this.csvData = csvData;
        this.verified = verified;
    }

    public Integer getId() {
        return Id;
    }



    public String getUsername() {
        return username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Register register = (Register) o;
        return Objects.equals(username, register.username) && Objects.equals(password, register.password) && Objects.equals(phoneNumber, register.phoneNumber) && Objects.equals(email, register.email) && Objects.equals(profile, register.profile) && Objects.equals(csvData, register.csvData);
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, phoneNumber, email, profile, csvData, verified);
    }

}