package com.example.course_registration_api.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationMs;

    public String generateJwtToken(int universityId, String password) {

        Date expirationDate = new Date((new Date()).getTime() + jwtExpirationMs);

        return Jwts.builder()
                .claim("universityId", universityId)
                .claim("password", password)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS384, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            System.out.println("token: " + token);
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            // invalid token
            throw e;
//            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            // expired token
            throw e;
//            System.out.println("Expired JWT token: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            // unsupported token
            throw e;
//            System.out.println("Unsupported JWT token: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // empty or null token
            throw e;
//            System.out.println("JWT claims string is empty: " + e.getMessage());
        }

//        return false;
    }

    public Map<String, Object> getClaimsFromJwtToken(String token) {

        if (token == null) {
            return Jwts.claims();
        }

        validateJwtToken(token);

        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        System.out.println("claims: " + claims);

        int universityId = Integer.parseInt(claims.get("universityId").toString());
        String password = (String) claims.get("password");

        Map<String, Object> tokenClaims = new HashMap<>();
        tokenClaims.put("universityId", universityId);
        tokenClaims.put("password", password);

        return tokenClaims;
    }
}