package com.tuan.authservice.exceptionhandler;

import com.tuan.authservice.exceptionhandler.builtin_exception.AbstractException;
import com.tuan.authservice.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value={AbstractException.class})
    public ResponseEntity<?> handleAbstractException(AbstractException ex, WebRequest request) {
        return ResponseEntity.status(ex.getStatus()).body(
                ApiResponse.builder()
                        .message(ex.getMessage())
                        .status(ex.getStatus().value())
                        .success(false)
                        .errors(ex)
                        .build()
        );
    }
}
