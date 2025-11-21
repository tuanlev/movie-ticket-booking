package com.tuan.authservice.exception;

import org.springframework.http.HttpStatus;

public class UsernameNotFoundException extends AbstractException {
    public UsernameNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
    public UsernameNotFoundException() {
        super("User not found", HttpStatus.NOT_FOUND);
    }
}
