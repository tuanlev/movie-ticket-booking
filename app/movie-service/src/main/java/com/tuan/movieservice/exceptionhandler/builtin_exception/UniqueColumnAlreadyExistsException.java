package com.tuan.movieservice.exceptionhandler.builtin_exception;

import org.springframework.http.HttpStatus;

public class UniqueColumnAlreadyExistsException extends AbstractException {
    public UniqueColumnAlreadyExistsException(String columnName) {
        super(columnName+" already exist", HttpStatus.CONFLICT);
    }
}
