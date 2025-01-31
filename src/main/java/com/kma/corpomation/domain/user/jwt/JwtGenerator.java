package com.kma.corpomation.domain.user.jwt;

import com.kma.corpomation.domain.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtGenerator {

    public String generateJwtToken(final Key SECRET, final long EXPIRATION, User user) {
        Long now = System.currentTimeMillis();

        return Jwts.builder()
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setSubject(String.valueOf(user.getId()))
                .setExpiration(new Date(now + EXPIRATION))
                .signWith(SECRET, SignatureAlgorithm.HS256)
                .compact();
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return header;
    }

    private Map<String, Object> createClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Identifier", String.valueOf(user.getId()));
        claims.put("Role", user.getUserRole());
        return claims;
    }
}