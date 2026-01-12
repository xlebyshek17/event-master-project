package com.kmironenka.eventmasterproject.controller;

import com.kmironenka.eventmasterproject.dto.UserDTO;
import com.kmironenka.eventmasterproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/my-account")
    public ResponseEntity<String> updateMyAccount(Authentication auth, @RequestBody UserDTO dto) {
        // Pobierasz ID zalogowanej osoby na podstawie loginu z tokena
        String login = auth.getName();
        Long userId = userService.getIdByLogin(login).orElseThrow(() -> new IllegalArgumentException(("Użytkownik nie istnieje!")));

        userService.updateUser(userId, dto);
        return ResponseEntity.ok("Dane konta zostały zaktualizowane!");
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMyAccount(Authentication auth) {
        String login  = auth.getName();
        Long userId =  userService.getIdByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException(("Użytkownik nie istnieje!")));

        Optional<UserDTO> u = userService.getUserById(userId);

        return u.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
