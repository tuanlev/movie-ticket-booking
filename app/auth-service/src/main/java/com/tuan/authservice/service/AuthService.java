package com.tuan.authservice.service;

import com.tuan.authservice.dto.LoginDTO;
import com.tuan.authservice.dto.RegisterDTO;
import com.tuan.authservice.exceptionhandler.builtin_exception.InvalidCredentialsException;
import com.tuan.authservice.exceptionhandler.builtin_exception.UniqueColumnAlreadyExistsException;
import com.tuan.authservice.exceptionhandler.builtin_exception.UserAlreadyExistsException;
import com.tuan.authservice.exceptionhandler.builtin_exception.UsernameNotFoundException;
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
public class AuthService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    @Autowired
    public void setAccountRepository(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //Need to provide jwt
    public ApiResponse login(LoginDTO loginDTO){
        Optional<User> userOptional = userRepository.findDistinctByUsername(loginDTO.getUsername());
        if (userOptional.isEmpty()) throw new UsernameNotFoundException();
        if (passwordEncoder
                .matches(loginDTO.getPassword(), userOptional.get().getPassword()))
            throw new InvalidCredentialsException();
        return ApiResponse.builder()
                .data(Map.of("user",userOptional.get()))
                .status(HttpStatus.OK.value())
                .success(true)
                .message("login successfully")
                .build();
    }

    public ApiResponse createUser(RegisterDTO registerDTO){
        // check username
        {
            Optional<User> userOptional = userRepository.findDistinctByUsername(registerDTO.getUsername());
            if (userOptional.isPresent()) throw new UserAlreadyExistsException();
        }
        // check gmail
        if (registerDTO.getGmail() != null){
            Optional<User> userOptional = userRepository.findDistinctByGmail(registerDTO.getGmail());
            if (userOptional.isPresent()) throw new UniqueColumnAlreadyExistsException("Gmail");
        }
        // check phoneNumber
        if (registerDTO.getPhoneNumber() != null){
            Optional<User> userOptional = userRepository.findDistinctByPhoneNumber(registerDTO.getPhoneNumber());
            if (userOptional.isPresent()) throw new UniqueColumnAlreadyExistsException("Phone number");
        }
        // Create user
        User user = userRepository.save(
                User.builder()
                .username(registerDTO.getUsername())
                .password(registerDTO.getPassword())
                        .gmail(registerDTO.getGmail())
                        .phoneNumber(registerDTO.getPhoneNumber())
                        .fullName(registerDTO.getFullName())
                        .role(registerDTO.getRole())
                .build()
        );

        return ApiResponse.builder()
                .message("Account created successfully")
                .data(Map.of("user",user))
                .status(HttpStatus.CREATED.value())
                .success(true)
                .build();

    }

}
