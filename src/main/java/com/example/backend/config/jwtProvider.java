package com.example.backend.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.security.Keys;

public class jwtProvider {

    // private static SecretKey secretKey =
    // Keys.hmacShaKeyFor(jwtConstant.JWT_SECRET.getBytes());
    private static SecretKey secretKey = jwtConstant.JWT_SECRET_KEY;

    public static String generateToken(Authentication auth) {
        String jwt = Jwts.builder()
                .setSubject(auth.getName())
                .claim("email", auth.getName())
                .setIssuer("mediaScocial")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 864000000))
                .signWith(secretKey)
                .compact();

        return jwt;
    }

    public static String getEmailFromJwtToken(String jwt) {

        jwt = jwt.substring(7);

        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        String email = String.valueOf(claims.get("email"));

        return email;

    }
}
