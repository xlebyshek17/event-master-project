package com.kmironenka.eventmasterproject.controller;

import com.kmironenka.eventmasterproject.dto.EventCreateDTO;
import com.kmironenka.eventmasterproject.dto.EventDTO;
import com.kmironenka.eventmasterproject.dto.OrganizerProfileDTO;
import com.kmironenka.eventmasterproject.service.EventService;
import com.kmironenka.eventmasterproject.service.OrganizerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizer")
public class OrganizerController {
    private final OrganizerService organizerService;
    private final EventService eventService;

    public OrganizerController(OrganizerService organizerService, EventService eventService) {
        this.organizerService = organizerService;
        this.eventService = eventService;
    }

    @PostMapping("/profile/{userId}")
    public ResponseEntity<String> createProfile(@PathVariable Long userId, @RequestBody OrganizerProfileDTO dto) {
        organizerService.createProfile(dto, userId);

        return ResponseEntity.ok("Profil organizatora utworzony!");
    }

    @PutMapping("/profile/{orgId}")
    public ResponseEntity<String> updateProfile(@PathVariable Long orgId, @RequestBody OrganizerProfileDTO dto) {
        organizerService.updateProfile(dto, orgId);

        return ResponseEntity.ok("Profil organizatora zaktualizowany!");
    }

    @GetMapping("/{orgId}/events")
    public ResponseEntity<List<EventDTO>> getAllEvents(@PathVariable Long orgId) {
        List<EventDTO> events = eventService.getAllByOrganizer(orgId);

        return ResponseEntity.ok(events);
    }

    @PostMapping("/{orgId}/events")
    public ResponseEntity<String> createEvent(@PathVariable Long orgId, @RequestBody EventCreateDTO dto) {
        eventService.addEvent(dto, orgId);

        return ResponseEntity.ok("Event created!");
    }

    @PutMapping("/{orgId}/events/{eventId}")
    public ResponseEntity<String> updateEvent(@PathVariable Long eventId, @RequestBody EventCreateDTO dto, @PathVariable Long orgId) {
        eventService.updateEvent(eventId, dto, orgId);

        return ResponseEntity.ok("Event updated!");
    }

    @DeleteMapping("/{orgId}/events/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId, @PathVariable Long orgId) {
        eventService.deleteEvent(eventId, orgId);

        return ResponseEntity.ok("Event deleted!");
    }
}
