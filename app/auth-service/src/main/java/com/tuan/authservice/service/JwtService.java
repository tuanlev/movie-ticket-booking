package com.tuan.authservice.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuan.authservice.model.User;
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

    public String generateAccessToken (User data) {
        return Jwts.builder()
                .claim("user",data)
                .expiration(new Date(System.currentTimeMillis()+accessExpiration))
                .signWith(Keys.hmacShaKeyFor(accessKey.getBytes()))
                .compact();
    }
    public String generateRefreshToken (User data) {
        return Jwts.builder()
                .claim("user",data)
                .expiration(new Date(System.currentTimeMillis()+refreshExpiration))
                .signWith(Keys.hmacShaKeyFor(refreshKey.getBytes()))
                .compact();
    }
    public String generateRotationToken (User data) {
        return Jwts.builder()
                .claim("user",data)
                .expiration(new Date(System.currentTimeMillis()+rotationExpiration))
                .signWith(Keys.hmacShaKeyFor(rotationKey.getBytes()))
                .compact();
    }

    public User verifyAccessToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(accessKey.getBytes()))
                .build().parseSignedClaims(token).getPayload();

        Map<String, Object> userMap = (Map<String, Object>) claims.get("user");
        return new ObjectMapper().convertValue(userMap, User.class);
    }
    public User verifyRefreshToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(refreshKey.getBytes()))
                .build().parseSignedClaims(token).getPayload();

        Map<String, Object> userMap = (Map<String, Object>) claims.get("user");
        return new ObjectMapper().convertValue(userMap, User.class);
    }
    public User verifyRotationToken(String token)  {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(rotationKey.getBytes()))
                .build().parseSignedClaims(token).getPayload();

        Map<String, Object> userMap = (Map<String, Object>) claims.get("user");
        return new ObjectMapper().convertValue(userMap, User.class);
    }
}