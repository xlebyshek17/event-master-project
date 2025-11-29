package com.kmironenka.eventmasterproject.service;

import com.kmironenka.eventmasterproject.dto.EventCategoryDTO;
import com.kmironenka.eventmasterproject.model.EventCategory;
import com.kmironenka.eventmasterproject.repository.EventCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventCategoryService {
    private final EventCategoryRepository repo;

    public EventCategoryService(EventCategoryRepository repo) {
        this.repo = repo;
    }

    public List<EventCategoryDTO> getAllCategories() {
        return repo.getAll().stream().map(this::mapToDTO).toList();
    }

    public Optional<EventCategoryDTO> getById(Integer id) {
        return repo.getById(id).map(this::mapToDTO);
    }

    public void addCategory(EventCategoryDTO dto)
    {
        if (repo.getByName(dto.getName()).isPresent()) {
            throw new IllegalArgumentException("Event category already exists");
        }

        EventCategory category = new EventCategory();
        category.setName(dto.getName());
        repo.addEventCategory(category);
    }

    public void deleteCategory(Integer id) {
        EventCategory category = repo.getById(id).orElse(null);
        if (category == null) {
            throw new IllegalArgumentException("Event category does not exist");
        }
        repo.deleteEventCategory(id);
    }

    public void updateCategory(EventCategoryDTO dto) {
        EventCategory category = repo.getById(dto.getId()).orElse(null);
        if (category == null) {
            throw new IllegalArgumentException("Event category does not exist");
        }
        category.setName(dto.getName());
        repo.updateEventCategory(category);
    }

    private EventCategoryDTO mapToDTO(EventCategory e) {
        EventCategoryDTO dto = new EventCategoryDTO();
        dto.setId(e.getCategoryId());
        dto.setName(e.getName());
        return dto;
    }
}
