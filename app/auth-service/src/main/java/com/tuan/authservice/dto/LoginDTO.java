package com.tuan.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "username required")
    String username;
    @NotBlank(message = "Password required")
    String password;
}
