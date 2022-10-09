package com.clients.app.exception;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
@Log4j2
public class ExceptionHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpResponseException> customParameterIsEmptyException(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error(methodArgumentNotValidException.getMessage());
        return createHttpResponse(HttpStatus.BAD_REQUEST, Objects.requireNonNull(methodArgumentNotValidException.getFieldError()).getDefaultMessage(), 204);
    }

    private ResponseEntity<HttpResponseException> createHttpResponse(HttpStatus httpStatus, String message, int customCode) {
        return new ResponseEntity<>(new HttpResponseException(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message,customCode), httpStatus);
    }
}
