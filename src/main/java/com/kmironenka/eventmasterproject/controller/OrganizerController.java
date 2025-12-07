package com.kmironenka.eventmasterproject.controller;

import com.kmironenka.eventmasterproject.dto.*;
import com.kmironenka.eventmasterproject.service.EventService;
import com.kmironenka.eventmasterproject.service.OrganizerService;
import com.kmironenka.eventmasterproject.service.TicketTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizer")
public class OrganizerController {
    private final OrganizerService organizerService;
    private final EventService eventService;
    private final TicketTypeService ticketTypeService;

    public OrganizerController(OrganizerService organizerService, EventService eventService, TicketTypeService ticketTypeService) {
        this.organizerService = organizerService;
        this.eventService = eventService;
        this.ticketTypeService = ticketTypeService;
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

    @GetMapping("/check-profile/{userdId}")
    public ResponseEntity<Boolean> hasProfile(@PathVariable Long userdId) {
        boolean exists = organizerService.isProfileExists(userdId);
        return ResponseEntity.ok(exists);
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

    @GetMapping("/{orgId}/events/{eventId}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable Long eventId, @PathVariable Long orgId) {
        EventDTO dto = eventService.getEvent(eventId, orgId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{orgId}/events/{eventId}/tickets")
    public ResponseEntity<List<TicketTypeDTO>> getAllTickets(@PathVariable Long eventId, @PathVariable Long orgId) {
        List<TicketTypeDTO> tickets = ticketTypeService.getAllByEvent(eventId, orgId);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/{orgId}/events/{eventId}/tickets")
    public ResponseEntity<String> createTicket(@PathVariable Long orgId,
                                               @PathVariable Long eventId,
                                               @RequestBody TicketTypeCreateDTO dto) {
        ticketTypeService.addTicketType(orgId, eventId, dto);

        return ResponseEntity.ok("Ticket created!");
    }

    @PutMapping("/{orgId}/events/{eventId}/tickets/{ticketId}")
    public ResponseEntity<String> updateTicket(@PathVariable Long orgId,
                                               @PathVariable Long eventId,
                                               @PathVariable Long ticketId,
                                               @RequestBody TicketTypeCreateDTO dto) {
        ticketTypeService.updateTicketType(orgId, eventId, ticketId, dto);
        return ResponseEntity.ok("Ticket updated!");
    }

    @DeleteMapping("/{orgId}/events/{eventId}/tickets/{ticketId}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long orgId,
                                               @PathVariable Long eventId,
                                               @PathVariable Long ticketId) {
        ticketTypeService.deleteTicketType(orgId, eventId, ticketId);
        return ResponseEntity.ok("Ticket deleted!");
    }
}
