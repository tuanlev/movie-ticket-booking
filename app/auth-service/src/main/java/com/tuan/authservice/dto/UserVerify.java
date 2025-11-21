package com.tuan.authservice.dto;

import com.tuan.authservice.model.role.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVerify {
    @NotBlank
    final String userId;
    @NotBlank
    final String username;
    @NotBlank
    final Role userRole;
}
