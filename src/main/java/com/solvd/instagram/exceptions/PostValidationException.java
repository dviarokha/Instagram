package com.solvd.instagram.exceptions;

public class PostValidationException extends RuntimeException {
    public PostValidationException(String message) {
        super(message);
    }
}
