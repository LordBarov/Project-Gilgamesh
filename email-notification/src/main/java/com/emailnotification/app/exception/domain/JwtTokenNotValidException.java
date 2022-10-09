package com.emailnotification.app.exception.domain;

public class JwtTokenNotValidException extends RuntimeException{
    public JwtTokenNotValidException(String message) {
        super(message);
    }
}
