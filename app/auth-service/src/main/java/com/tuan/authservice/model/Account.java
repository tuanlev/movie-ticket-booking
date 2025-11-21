package com.tuan.authservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Email(message = "invalid ")
    @NotBlank(message = "")
    String gmail;
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid phone number")
    String phoneNumber;
    @NotBlank(message = "Password required")
    String password;
    String fullName;
    @Builder.Default()
    Role role = Role.user;
}
