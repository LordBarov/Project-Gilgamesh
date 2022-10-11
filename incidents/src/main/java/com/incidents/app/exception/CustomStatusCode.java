package com.incidents.app.exception;

public enum CustomStatusCode {

    NOT_FOUND_IN_DB("104"),

    COULD_NOT_CREATE_RECORD_IN_DB("105"),

    COULD_NOT_DELETE_RECORD_IN_DB("106"),

    JWT_TOKEN_NOT_VALID("107"),

    FIELD_IS_NOT_VALID("108"),

    AUTHENTICATION_ERROR("109");

    private final String code;

    CustomStatusCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
