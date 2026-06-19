package com.arp.erp_backend.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${SECRET_KEY}")
    private String secret_key;

    @Value("${JWTExpirationMS}")
    private int jwtExpirationMS;

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMS))
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secret_key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
