package com.authentication.app.exception.domain;

public class CustomCouldNotCreateException extends RuntimeException{
    public CustomCouldNotCreateException(String message) {
        super(message);
    }
}
