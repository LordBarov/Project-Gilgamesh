package com.emailnotification.app.exception.domain;

public class CustomCouldNotDeleteException extends RuntimeException{
    public CustomCouldNotDeleteException(String message) {
        super(message);
    }
}
