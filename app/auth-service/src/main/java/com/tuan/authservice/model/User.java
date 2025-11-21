package com.tuan.authservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.tuan.authservice.model.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    Role role = Role.USER;
    @Builder.Default
    Timestamp created = new Timestamp(System.currentTimeMillis());
}
