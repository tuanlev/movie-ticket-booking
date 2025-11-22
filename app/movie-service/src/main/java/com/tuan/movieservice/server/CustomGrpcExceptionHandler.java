package com.tuan.movieservice.server;

import com.tuan.authservice.exceptionhandler.builtin_exception.UsernameNotFoundException;
import io.grpc.Status;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class CustomGrpcExceptionHandler {
    @GrpcExceptionHandler({JwtException.class, UsernameNotFoundException.class})
    public Status handleJwtException(Exception ex) {
        if  (ex instanceof ExpiredJwtException) {
            return Status.UNAUTHENTICATED.withDescription("token expired " + ex.getMessage());
        } else
            return Status.UNAUTHENTICATED.withDescription("token invalid "+ ex.getMessage());
    }
    @GrpcExceptionHandler(Exception.class)
    public Status handleGeneralException(Exception e) {
        return Status.INTERNAL
                .withDescription("Internal server error: " + e.getMessage())
                .withCause(e);
    }
}
