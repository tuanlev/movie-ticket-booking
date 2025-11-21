package com.tuan.authservice.dto;

import com.tuan.authservice.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterDTO {
    @NotBlank(message = "username required")
    String username;
    @Email(message = "invalid ")
    String gmail;
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid phone number")
    String phoneNumber;
    @NotBlank(message = "Password required")
    String password;
    String fullName;
    Role role ;
}
