package com.kmironenka.eventmasterproject.service;

import com.kmironenka.eventmasterproject.dto.EventCreateDTO;
import com.kmironenka.eventmasterproject.dto.EventDTO;
import com.kmironenka.eventmasterproject.model.Event;
import com.kmironenka.eventmasterproject.model.EventStatus;
import com.kmironenka.eventmasterproject.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepo;

    public EventService(EventRepository eventRepo) {
        this.eventRepo = eventRepo;
    }

    public List<EventDTO> getAllByOrganizer(Long orgId) {
        return eventRepo.getAll(orgId).stream().map(this::mapToDTO).toList();
    }

    public List<EventDTO> getPublishedEvents() {
        return eventRepo.getPublishedEvents().stream().map(this::mapToDTO).toList();
    }

    public void addEvent(EventCreateDTO dto, Long organizerId) {
        if (dto.getStartTime().isBefore(java.time.OffsetDateTime.now())) {
            throw new IllegalArgumentException("Data rozpoczęcia nie może być w przeszłości.");
        }

        if (eventRepo.existsByDetails(dto.getTitle(), dto.getVenueId(), dto.getStartTime())) {
            throw new IllegalArgumentException("Event already exists");
        }

        Event event = new Event();
        mapFromDTO(dto, event);
        event.setOrganizerId(organizerId);
        event.setStatus(EventStatus.SZKIC);

        eventRepo.addEvent(event);
    }

    public void updateEvent(Long eventId, EventCreateDTO dto, Long organizerId) {
        if (dto.getStartTime().isBefore(java.time.OffsetDateTime.now())) {
            throw new IllegalArgumentException("Nie można zmienić daty rozpoczęcia na termin z przeszłości.");
        }

        Event event = new Event();
        mapFromDTO(dto, event);

        event.setEventId(eventId);
        event.setOrganizerId(organizerId);

        int rowsAffected = eventRepo.updateEvent(event);

        if (rowsAffected == 0) {
            throw new IllegalArgumentException("Event does not exist!");
        }
    }

    public void deleteEvent(Long eventId, Long organizerId) {
        int rowsAffected = eventRepo.deleteEvent(eventId, organizerId);

        if (rowsAffected == 0) {
            throw new IllegalArgumentException("Event does not exist!");
        }
    }

    public EventDTO getEvent(Long eventId, Long orgId) {
        Event event = eventRepo.getEventById(eventId).orElseThrow(() -> new IllegalArgumentException("Wydarzenie nie istnieje"));

        if (!event.getOrganizerId().equals(orgId)) {
            throw new IllegalArgumentException("Brak dostepu do tego wydarzenia");
        }

        return mapToDTO(event);
    }

    public EventDTO getPublishedEvent(Long eventId) {
        Event event = eventRepo.getPublishedEventById(eventId).orElseThrow(() -> new IllegalArgumentException("Wydarzenie nie istnieje"));
        return mapToDTO(event);
    }

    private EventDTO mapToDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setEventId(event.getEventId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setStartTime(event.getStartTime());
        dto.setEndTime(event.getEndTime());
        dto.setImageUrl(event.getImageUrl());
        dto.setVenueId(event.getVenueId());
        dto.setCategoryId(event.getCategoryId());


        dto.setOrganizerName(event.getOrganizerName());
        dto.setVenueName(event.getVenueName());
        dto.setCategoryName(event.getCategoryName());
        dto.setStatus(event.getStatus());
        dto.setCity(event.getCity());
        dto.setAddress(event.getAddress());
        dto.setMinPrice(event.getMinPrice());

        return dto;
    }

    private void mapFromDTO(EventCreateDTO dto, Event event) {
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setStartTime(dto.getStartTime());
        event.setEndTime(dto.getEndTime());
        event.setVenueId(dto.getVenueId());
        event.setCategoryId(dto.getCategoryId());
        event.setStatus(dto.getStatus());
        event.setImageUrl(dto.getImageUrl());
    }
}
