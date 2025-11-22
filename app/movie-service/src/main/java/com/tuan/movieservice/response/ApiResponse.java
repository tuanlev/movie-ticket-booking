package com.tuan.movieservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    int status;
    String message;
    Object data;
    Object errors;
    boolean success;
    @Builder.Default
    LocalDateTime  timestamp =  LocalDateTime.now();
}
