package com.SpringBoot.springbootRef.Registration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTest {

    @Test
    public void testGettersAndSetters() {
        Register register = new Register();

        register.setUsername("testUser");
        register.setPassword("testPassword");
        register.setPhoneNumber("12345");
        register.setEmail("test@example.com");
        register.setProfile("testProfile");
        register.setCsvData("testCsvData");
        register.setVerified("Yes");

        assertEquals("testUser", register.getUsername());
        assertEquals("testPassword", register.getPassword());
        assertEquals("12345", register.getPhoneNumber());
        assertEquals("test@example.com", register.getEmail());
        assertEquals("testProfile", register.getProfile());
        assertEquals("testCsvData", register.getCsvData());
        assertEquals("Yes", register.getVerified());
    }

    @Test
    public void testEqualsAndHashCode() {
        Register register1 = new Register(1,"user1", "password", "12345", "email@example.com", "profile", "csvData", "Yes");
        Register register2 = new Register(2, "user1", "password", "12345", "email@example.com", "profile", "csvData", "Yes");

        assertEquals(register1, register2);

        assertEquals(register1.hashCode(), register2.hashCode());
    }
}

