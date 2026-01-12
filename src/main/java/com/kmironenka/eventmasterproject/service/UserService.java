package com.kmironenka.eventmasterproject.service;

import com.kmironenka.eventmasterproject.dto.UserDTO;
import com.kmironenka.eventmasterproject.model.User;
import com.kmironenka.eventmasterproject.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repository) {
        this.repo = repository;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = repo.getAll();

        return users.stream().map(this::mapToDTO).toList();
    }

    public void deleteUser(Long userId) {
        int rowsAffected = repo.deleteUser(userId);

        if (rowsAffected == 0) {
            throw new IllegalArgumentException("User does not exist!");
        }
    }

    public Optional<UserDTO> getUserById(Long userId) {

        return repo.getById(userId).map(this::mapToDTO);
    }

    public Optional<Long> getIdByLogin(String login) {

        return repo.getIdByLogin(login);
    }

    public void updateUser(Long userId, UserDTO dto) {
        User u = repo.getById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Użytkownik nie istnieje!"));

        // Aktualizacja podstawowych danych
        u.setName(dto.getName());
        u.setSurname(dto.getSurname());
        u.setEmail(dto.getEmail());

        if (dto.getNewPassword() != null && !dto.getNewPassword().isEmpty()) {
            String salt = BCrypt.gensalt(12);
            u.setPasswordHash(BCrypt.hashpw(dto.getNewPassword(), salt));
            System.out.println();
        }

        repo.updateUser(u);
    }

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getUserId());
        dto.setLogin(user.getLogin());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setRole(repo.getUserRoleName(user.getUserId()));
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}
