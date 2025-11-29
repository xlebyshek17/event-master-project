package com.kmironenka.eventmasterproject.controller;

import com.kmironenka.eventmasterproject.dto.UserDTO;
import com.kmironenka.eventmasterproject.dto.LoginDTO;
import com.kmironenka.eventmasterproject.dto.RegisterDTO;
import com.kmironenka.eventmasterproject.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO dto) {
        authService.register(dto);
        return ResponseEntity.ok("Użytkownik został zarejestrowany pomyślnie!");
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO dto) {
        UserDTO u = authService.login(dto);

        return ResponseEntity.ok(u);
    }
}
