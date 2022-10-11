package com.authentication.app.exception;


import com.authentication.app.exception.domain.CustomCouldNotCreateException;
import com.authentication.app.exception.domain.CustomCouldNotDeleteException;
import com.authentication.app.exception.domain.CustomNotFoundException;
import com.authentication.app.exception.domain.JwtTokenNotValidException;
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

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<HttpResponseException> customNotFoundException(CustomNotFoundException customNotFoundException) {
        log.error(customNotFoundException.getMessage());
        return createHttpResponse(HttpStatus.NOT_FOUND, customNotFoundException.getMessage(), 104);
    }

    @ExceptionHandler(CustomCouldNotCreateException.class)
    public ResponseEntity<HttpResponseException> customCouldNotCreateException(CustomCouldNotCreateException customCouldNotCreateException) {
        log.error(customCouldNotCreateException.getMessage());
        return createHttpResponse(HttpStatus.BAD_REQUEST, customCouldNotCreateException.getMessage(), 105);
    }

    @ExceptionHandler(CustomCouldNotDeleteException.class)
    public ResponseEntity<HttpResponseException> customCouldNotCreateException(CustomCouldNotDeleteException customCouldNotDeleteException) {
        log.error(customCouldNotDeleteException.getMessage());
        return createHttpResponse(HttpStatus.BAD_REQUEST, customCouldNotDeleteException.getMessage(), 106);
    }

    @ExceptionHandler(JwtTokenNotValidException.class)
    public ResponseEntity<HttpResponseException> customCouldNotValidateTokenException(JwtTokenNotValidException jwtTokenNotValidException) {
        log.error(jwtTokenNotValidException.getMessage());
        return createHttpResponse(HttpStatus.BAD_REQUEST, "Ошибка Валидации", 107);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpResponseException> customParameterIsEmptyException(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error(methodArgumentNotValidException.getMessage());
        return createHttpResponse(HttpStatus.BAD_REQUEST, Objects.requireNonNull(methodArgumentNotValidException.getFieldError()).getDefaultMessage(), 108);
    }

    private ResponseEntity<HttpResponseException> createHttpResponse(HttpStatus httpStatus, String message, int customCode) {
        return new ResponseEntity<>(new HttpResponseException(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message,customCode), httpStatus);
    }
}
