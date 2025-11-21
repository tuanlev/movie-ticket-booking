package com.tuan.authservice.service;

import com.tuan.authservice.dto.LoginDTO;
import com.tuan.authservice.exception.InvalidCredentialsException;
import com.tuan.authservice.exception.UsernameNotFoundException;
import com.tuan.authservice.model.User;
import com.tuan.authservice.repository.UserRepository;
import com.tuan.authservice.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    @Autowired
    public void setAccountRepository(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public ApiResponse createAccount(LoginDTO loginDTO){
        Optional<User> userOptional = userRepository.findDistinctByUsername(loginDTO.getUsername());
        if (!userOptional.isPresent()) throw new UsernameNotFoundException();
        if (passwordEncoder
                .matches(loginDTO.getPassword(), userOptional.get().getPassword()))
            throw new InvalidCredentialsException();
        return ApiResponse.builder()
                .data(Map.of("user",userOptional.get()))
                .status(HttpStatus.OK.value())
                .success(true)
                .message("Account created successfully")
                .build();
    }

}
