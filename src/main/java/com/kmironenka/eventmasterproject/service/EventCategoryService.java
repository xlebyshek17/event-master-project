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
        int rowsAffected = repo.deleteEventCategory(id);

        if (rowsAffected == 0) {
            throw new IllegalArgumentException("Event category does not exist");
        }
    }

    public void updateCategory(EventCategoryDTO dto, Integer id) {
        EventCategory category = new EventCategory();

        category.setCategoryId(id);
        category.setName(dto.getName());

        int rowsAffected = repo.updateEventCategory(category);

        if (rowsAffected == 0) {
            throw new IllegalArgumentException("Event category does not exist");
        }
    }

    private EventCategoryDTO mapToDTO(EventCategory e) {
        EventCategoryDTO dto = new EventCategoryDTO();
        dto.setId(e.getCategoryId());
        dto.setName(e.getName());
        return dto;
    }
}
