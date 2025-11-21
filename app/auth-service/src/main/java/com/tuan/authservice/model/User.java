package com.tuan.authservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @JsonIgnore
    String password;
    String fullName;
    @Builder.Default()
    Role role = Role.user;
}
