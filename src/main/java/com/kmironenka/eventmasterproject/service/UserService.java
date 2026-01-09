package com.kmironenka.eventmasterproject.service;

import com.kmironenka.eventmasterproject.dto.UserDTO;
import com.kmironenka.eventmasterproject.model.User;
import com.kmironenka.eventmasterproject.repository.UserRepository;
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
