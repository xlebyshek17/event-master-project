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

    public void addEvent(EventCreateDTO dto, Long organizerId) {
        Event event = new Event();
        mapFromDTO(dto, event);

        event.setOrganizerId(organizerId);
        event.setStatus(EventStatus.OPUBLIKOWANE);

        eventRepo.addEvent(event);
    }

    public void updateEvent(Long eventId, EventCreateDTO dto, Long organizerId) {
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

    private EventDTO mapToDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setStartTime(event.getStartTime());
        dto.setEndTime(event.getEndTime());

        dto.setOrganizerName(event.getOrganizerName());
        dto.setVenueName(event.getVenueName());
        dto.setCategoryName(event.getCategoryName());
        dto.setStatus(event.getStatus());

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
    }
}
