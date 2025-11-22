package com.tuan.movieservice.exceptionhandler.builtin_exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends AbstractException {
    public InvalidCredentialsException() {
        super("invalid username or password", HttpStatus.UNAUTHORIZED);
    }
    public InvalidCredentialsException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}