package com.tuan.authservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @NotBlank(message = "username required")
    @Column(unique = true)
    String username;
    @Column(unique = true)
    @Email(message = "invalid ")
    String gmail;
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid phone number")
    @Column(unique = true)
    String phoneNumber;
    @NotBlank(message = "Password required")
    String password;
    String fullName;
    @Builder.Default()
    Role role = Role.user;
}
