package com.clients.app.exception;

public enum CustomStatusCode {

    FIELD_IS_EMPTY("204");

    private final String code;

    CustomStatusCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
