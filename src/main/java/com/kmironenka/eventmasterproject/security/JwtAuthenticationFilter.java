package com.kmironenka.eventmasterproject.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userLogin;

        // 1. Sprawdzamy, czy nagłówek istnieje i zaczyna się od "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Wyciągamy sam token (ucinamy "Bearer ")
        jwt = authHeader.substring(7);

        // Wyciągamy login z tokena (jeśli token jest nieprawidłowy, tu poleci wyjątek, który warto by obsłużyć, ale na start wystarczy)
        try {
            userLogin = jwtService.extractUsername(jwt);
        } catch (Exception e) {
            // DODAJ TĘ LINIĘ, aby zobaczyć błąd w konsoli IntelliJ
            System.out.println("BŁĄD JWT: " + e.getMessage());
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Jeśli mamy login, a użytkownik nie jest jeszcze zalogowany w kontekście Springa
        if (userLogin != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Sprawdzamy ważność tokena
            if (jwtService.isTokenValid(jwt, userLogin)) {

                // Wyciągamy rolę bezpośrednio z tokena (żeby nie pytać bazy danych przy każdym kliknięciu!)
                Claims claims = jwtService.extractClaim(jwt, c -> c);
                String role = claims.get("role", String.class);

                // Tworzymy uprawnienie dla Spring Security (np. ROLE_USER)
                // Spring Security lubi prefix "ROLE_"
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

                // Tworzymy obiekt Authentication
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userLogin, // Principal (to co potem wyciągasz jako auth.getName())
                        null,      // Credentials (brak hasła, bo już zweryfikowane tokenem)
                        Collections.singletonList(authority) // Lista ról
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 4. Ustawiamy użytkownika jako "ZALOGOWANY" w obecnym wątku
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Idziemy do następnego filtra
        filterChain.doFilter(request, response);
    }
}