package com.kmironenka.eventmasterproject.service;

import com.kmironenka.eventmasterproject.dto.AuthResponseDTO;
import com.kmironenka.eventmasterproject.dto.UserDTO;
import com.kmironenka.eventmasterproject.model.User;
import com.kmironenka.eventmasterproject.dto.LoginDTO;
import com.kmironenka.eventmasterproject.dto.RegisterDTO;
import com.kmironenka.eventmasterproject.repository.RoleRepository;
import com.kmironenka.eventmasterproject.repository.UserRepository;
import com.kmironenka.eventmasterproject.security.JwtService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.mindrot.jbcrypt.BCrypt;

import java.time.OffsetDateTime;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepo, RoleRepository roleRepo, JwtService jwtService) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.jwtService = jwtService;
    }

    @Transactional
    public void register(RegisterDTO dto) {
        if (userRepo.getByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (userRepo.getByLogin(dto.getLogin()).isPresent()) {
            throw new IllegalArgumentException("Login already exists");
        }

        String salt = BCrypt.gensalt(12);
        String hashPassword = BCrypt.hashpw(dto.getPassword(), salt);

        User u = new User();
        u.setEmail(dto.getEmail());
        u.setLogin(dto.getLogin());
        u.setPasswordHash(hashPassword);
        u.setName(dto.getName());
        u.setSurname(dto.getSurname());
        u.setCreatedAt(OffsetDateTime.now());

        Long userId = userRepo.addUser(u);

        String roleName = dto.getRole() != null ? dto.getRole() : "USER";
        Integer roleId = roleRepo.getRoleId(roleName);

        userRepo.setRoleToUser(userId, roleId);
    }

    @Transactional
    public AuthResponseDTO login(LoginDTO dto) {
        User u = userRepo.getByLogin(dto.getLogin())
                .orElseThrow(() -> new IllegalArgumentException("Błędny login"));


        if (!BCrypt.checkpw(dto.getPassword(), u.getPasswordHash())) {
            throw new IllegalArgumentException("Błędne hasło");
        }

        String role = userRepo.getUserRoleName(u.getUserId());
        String token = jwtService.generateToken(u.getLogin(), role);

        AuthResponseDTO response  = new AuthResponseDTO();
        response.setId(u.getUserId());
        response.setLogin(u.getLogin());
        response.setToken(token);
        response.setRole(role);

        return response;
    }
}
