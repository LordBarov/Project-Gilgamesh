package com.emailnotification.app.exception.domain;

public class CustomCouldNotCreateException extends RuntimeException{
    public CustomCouldNotCreateException(String message) {
        super(message);
    }
}
