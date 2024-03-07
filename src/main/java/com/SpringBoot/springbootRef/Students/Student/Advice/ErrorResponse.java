package com.SpringBoot.springbootRef.Students.Student.Advice;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private int status;
    private String message;

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    // Getters
}
