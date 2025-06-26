package org.example.moreeduceoriginapplication.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {
    private final String secret="esfuhseughseurkghawilgulwghvkauflishdgillizshrglizsfhbgvlzkjbishvlizshzlsugh";
    private final Long expired=1000*60*60*24L;
    private SecretKey key;

    @PostConstruct
    public void init(){
        key = Keys.hmacShaKeyFor(Base64.getEncoder().encode(secret.getBytes()));
    }

    public String createToken(String email){
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+expired))
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isValid(String token) {
        try {
            extractEmail(token);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }
}
