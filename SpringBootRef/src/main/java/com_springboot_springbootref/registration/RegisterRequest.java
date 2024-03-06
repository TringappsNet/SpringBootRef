package com_springboot_springbootref.registration;

public record RegisterRequest(Integer Id,String username, String password, String phoneNumber, String email, String profile, String csvData, String verified) {
}
