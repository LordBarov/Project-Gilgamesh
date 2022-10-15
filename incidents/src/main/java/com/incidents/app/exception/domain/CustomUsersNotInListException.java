package com.incidents.app.exception.domain;

public class CustomUsersNotInListException extends RuntimeException{
    public CustomUsersNotInListException(String message) {
        super(message);
    }
}
