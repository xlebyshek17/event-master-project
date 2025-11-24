package com.kmironenka.eventmasterproject.service;

import com.kmironenka.eventmasterproject.model.User;
import com.kmironenka.eventmasterproject.repository.LoginDTO;
import com.kmironenka.eventmasterproject.repository.RegisterDTO;
import com.kmironenka.eventmasterproject.repository.RoleRepository;
import com.kmironenka.eventmasterproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.mindrot.jbcrypt.BCrypt;

import java.time.OffsetDateTime;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    public AuthService(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
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
    public User login(LoginDTO dto) {
        User u = userRepo.getByLogin(dto.getLogin()).orElseThrow(() -> new IllegalArgumentException("Błędny login"));

        if (!BCrypt.checkpw(dto.getPassword(), u.getPasswordHash())) {
            throw new IllegalArgumentException("Błędne hasło");
        }

        return u;
    }
}
