package com.tuan.authservice.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuan.authservice.dto.UserVerify;
import com.tuan.authservice.model.User;
import com.tuan.authservice.model.role.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class  JwtService {
    @Value("${jwt.access.key}")
    private String accessKey;
    @Value("${jwt.access.expiration}")
    private int accessExpiration;
    @Value("${jwt.refresh.key}")
    private String refreshKey;
    @Value("${jwt.refresh.expiration}")
    private int refreshExpiration;
    @Value("${jwt.rotation.key}")
    private String rotationKey;
    @Value("${jwt.rotation.expiration}")
    private int rotationExpiration;

    public String generateAccessToken (UserVerify data) {
        return Jwts.builder()
                .claim("userId",data.getUserId())
                .claim("userName",data.getUsername())
                .claim("userRole",data.getUserRole())
                .expiration(new Date(System.currentTimeMillis()+accessExpiration))
                .signWith(Keys.hmacShaKeyFor(accessKey.getBytes()))
                .compact();
    }
    public String generateRefreshToken (UserVerify data) {
        return Jwts.builder()
                .claim("userId",data.getUserId())
                .claim("userName",data.getUsername())
                .claim("userRole",data.getUserRole())
                .expiration(new Date(System.currentTimeMillis()+accessExpiration))
                .signWith(Keys.hmacShaKeyFor(refreshKey.getBytes()))
                .compact();
    }
    public String generateRotationToken (UserVerify data) {
        return Jwts.builder()
                .claim("userId",data.getUserId())
                .claim("userName",data.getUsername())
                .claim("userRole",data.getUserRole())
                .expiration(new Date(System.currentTimeMillis()+rotationExpiration))
                .signWith(Keys.hmacShaKeyFor(rotationKey.getBytes()))
                .compact();
    }
    public String generateAccessToken (User data) {
        return Jwts.builder()
                .claim("userId",data.getId())
                .claim("userName",data.getUsername())
                .claim("userRole",data.getRole())
                .expiration(new Date(System.currentTimeMillis()+accessExpiration))
                .signWith(Keys.hmacShaKeyFor(accessKey.getBytes()))
                .compact();
    }
    public String generateRefreshToken (User data) {
        return Jwts.builder()
                .claim("userId",data.getId())
                .claim("userName",data.getUsername())
                .claim("userRole",data.getRole())
                .expiration(new Date(System.currentTimeMillis()+accessExpiration))
                .signWith(Keys.hmacShaKeyFor(refreshKey.getBytes()))
                .compact();
    }
    public String generateRotationToken (User data) {
        return Jwts.builder()
                .claim("userId",data.getId())
                .claim("username",data.getUsername())
                .claim("userRole",data.getRole())                .expiration(new Date(System.currentTimeMillis()+rotationExpiration))
                .signWith(Keys.hmacShaKeyFor(rotationKey.getBytes()))
                .compact();
    }

    public UserVerify verifyAccessToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(accessKey.getBytes()))
                .build().parseSignedClaims(token).getPayload();

//        Map<String, Object> userMap = (Map<String, Object>) claims.get("user");
        return UserVerify.builder()
                .userId(claims.get("userId",String.class))
                .username(claims.get("userName",String.class))
                .userRole(Role.valueOf(claims.get("userRole", String.class)))
                .build();
    }
    public UserVerify verifyRefreshToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(refreshKey.getBytes()))
                .build().parseSignedClaims(token).getPayload();

        return UserVerify.builder()
                .userId(claims.get("userId",String.class))
                .username(claims.get("userName",String.class))
                .userRole(Role.valueOf(claims.get("userRole", String.class)))
                .build();
    }
    public UserVerify verifyRotationToken(String token)  {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(rotationKey.getBytes()))
                .build().parseSignedClaims(token).getPayload();

        return UserVerify.builder()
                .userId(claims.get("userId",String.class))
                .username(claims.get("userName",String.class))
                .userRole(Role.valueOf(claims.get("userRole", String.class)))
                .build();
    }
}