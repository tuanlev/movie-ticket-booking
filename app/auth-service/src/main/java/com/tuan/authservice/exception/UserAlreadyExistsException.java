package com.tuan.authservice.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends AbstractException {
    public UserAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
    public UserAlreadyExistsException() {
        super("user already exist", HttpStatus.CONFLICT);
    }
}
