package com.tuan.authservice.dto;

import com.tuan.authservice.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "username required")
    String username;
    @NotBlank(message = "Password required")
    String password;
}
