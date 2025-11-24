package com.kmironenka.eventmasterproject.service;

import com.kmironenka.eventmasterproject.model.User;
import com.kmironenka.eventmasterproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repository) {
        this.repo = repository;
    }

    public List<User> getAllUsers() {
        return repo.getAll();
    }
}
