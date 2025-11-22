package com.tuan.movieservice.exceptionhandler;

import com.tuan.authservice.exceptionhandler.builtin_exception.AbstractException;
import com.tuan.authservice.response.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptionHandler {
    // for (validation, data logic)
    @ExceptionHandler(value={AbstractException.class})
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
    // jwt error handler
    @ExceptionHandler(value = {JwtException.class})
    public ResponseEntity<?> handleJwtException(JwtException ex, WebRequest request) {
        if (ex instanceof ExpiredJwtException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    ApiResponse.builder()
                            .message("token expired")
                            .status(HttpStatus.UNAUTHORIZED.value())
                            .success(false)
                            .build()
            );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ApiResponse.builder()
                .message("invalid token")
                .status(HttpStatus.UNAUTHORIZED.value())
                .success(false)
                .build()
        );
    }
}
