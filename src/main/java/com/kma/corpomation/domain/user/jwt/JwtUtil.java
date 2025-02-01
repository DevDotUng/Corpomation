package com.kma.corpomation.domain.user.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

@Service
public class JwtUtil {
    public Key getSigningKey(String secretKey) {
        String encodedKey = encodeToBase64(secretKey);

        return Keys.hmacShaKeyFor(encodedKey.getBytes(StandardCharsets.UTF_8));
    }

    private String encodeToBase64(String secretKey) {
        return Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
}