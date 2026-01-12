package com.kmironenka.eventmasterproject.controller;

import com.kmironenka.eventmasterproject.dto.EventDTO;
import com.kmironenka.eventmasterproject.dto.TicketTypeDTO;
import com.kmironenka.eventmasterproject.service.EventService;
import com.kmironenka.eventmasterproject.service.TicketTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class PublicEventController {
    EventService eventService;
    TicketTypeService ticketService;

    public PublicEventController(EventService eventService, TicketTypeService ticketService) {
        this.eventService = eventService;
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getEvents() {
        return ResponseEntity.ok(eventService.getPublishedEvents());
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable Long eventId) {
        EventDTO e = eventService.getPublishedEvent(eventId);
        return ResponseEntity.ok(e);
    }

    @GetMapping("/{eventId}/tickets")
    public ResponseEntity<List<TicketTypeDTO>> getTickets(@PathVariable Long eventId) {
        List<TicketTypeDTO> tickets = ticketService.getTicketsForPublicEvent(eventId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{eventId}/similar")
    public ResponseEntity<List<EventDTO>> getSimilarEvents(@PathVariable Long eventId, @RequestParam int categoryId) {
        List<EventDTO> similarEvents = eventService.getSimilarEvents(eventId, categoryId);
        return ResponseEntity.ok(similarEvents);
    }
}
