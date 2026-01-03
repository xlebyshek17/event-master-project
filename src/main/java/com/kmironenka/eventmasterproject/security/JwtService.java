package com.kmironenka.eventmasterproject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // KLUCZ SEKRETNY - W prawdziwym projekcie trzymaj to w application.properties!
    // Musi być długi i skomplikowany (min. 256 bitów dla HS256)
    private static final String SECRET_KEY = "ToJestBardzoTajnyKluczKtoryPowinienBycDlugiiSkomplikowany123456";

    // Generowanie tokena (dla Usera)
    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role); // Dodajemy rolę do tokena, żeby wiedzieć kim jest user
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject) // Tutaj wpisujemy Login
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // Ważny 24h
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Wyciąganie Loginu z tokena
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Wyciąganie innych danych (np. daty ważności)
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Sprawdzanie czy token jest ważny
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private Key getSigningKey() {
        // Jeśli klucz jest zwykłym tekstem, można go użyć tak, ale bezpieczniej zakodować Base64
        // Tutaj dla uproszczenia używamy bajtów z naszego Stringa
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}