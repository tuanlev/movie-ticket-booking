package com.tuan.authservice.controller;

import com.tuan.authservice.dto.LoginDTO;
import com.tuan.authservice.dto.RegisterDTO;
import com.tuan.authservice.response.ApiResponse;
import com.tuan.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    AuthService authService;
    @Autowired
    public void setDependenciesS(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO) {
        ApiResponse response = authService.createUser(registerDTO);
        return ResponseEntity.status(response
                .getStatus()).body(response);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        ApiResponse response = authService.login(loginDTO);
        return ResponseEntity.status(response
                .getStatus()).body(response);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader(value = "refresh-token", required = false) String refreshToken ) {
        ApiResponse response = authService.refresh(refreshToken);
        return ResponseEntity.status(response
                .getStatus()).body(response);
    }

}

