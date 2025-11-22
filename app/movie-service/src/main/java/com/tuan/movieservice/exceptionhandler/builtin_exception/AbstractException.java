package com.tuan.movieservice.exceptionhandler.builtin_exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AbstractException extends RuntimeException {
    private final HttpStatus status;   // HTTP Code (400, 401, 403...)
    public AbstractException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
