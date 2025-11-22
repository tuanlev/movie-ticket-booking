package com.tuan.movieservice.exceptionhandler.builtin_exception;

import com.tuan.authservice.exceptionhandler.builtin_exception.AbstractException;
import org.springframework.http.HttpStatus;

public class UsernameNotFoundException extends AbstractException {
    public UsernameNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
    public UsernameNotFoundException() {
        super("User not found", HttpStatus.NOT_FOUND);
    }
}
