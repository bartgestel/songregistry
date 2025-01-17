package com.bartvangestel.songregistrybackend.logic;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String secretKey = "XKMS0RY93q5LX6X9nhkSmKMkYs3JADpJ";

    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }

    public String extractEmail(String token){
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token, String email){
        return (!isTokenExpired(token) && extractEmail(token).equals(email));
    }
}
