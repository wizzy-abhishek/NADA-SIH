package com.ai.aiml10.service;

import com.ai.aiml10.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey ;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(UserEntity user){

        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("email" , user.getEmail())
                .claim("roles" , user.getRoles().toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*60*24 )) //60 mins 1000*60*60
                .signWith(getSecretKey())
                .compact() ;

    }

    public String generateRefreshToken(UserEntity user) {

        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000L *60*60*24*30)) // 1 month
                .signWith(getSecretKey())
                .compact() ;
    }

    public Long getUserIdFromToken(String token){

        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload() ;

        return Long.valueOf(claims.getSubject()) ;
    }
}