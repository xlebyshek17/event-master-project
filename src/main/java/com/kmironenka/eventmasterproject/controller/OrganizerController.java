package com.kmironenka.eventmasterproject.controller;

import com.kmironenka.eventmasterproject.dto.*;
import com.kmironenka.eventmasterproject.service.EventService;
import com.kmironenka.eventmasterproject.service.OrganizerService;
import com.kmironenka.eventmasterproject.service.TicketTypeService;
import com.kmironenka.eventmasterproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizer")
public class OrganizerController {
    private final OrganizerService organizerService;
    private final EventService eventService;
    private final TicketTypeService ticketTypeService;
    private final UserService userService;

    public OrganizerController(OrganizerService organizerService, EventService eventService, TicketTypeService ticketTypeService, UserService userService) {
        this.organizerService = organizerService;
        this.eventService = eventService;
        this.ticketTypeService = ticketTypeService;
        this.userService = userService;
    }

    private Long getLoggedUserId(Authentication auth) {
        String login  = auth.getName();
        return userService.getIdByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException(("Użytkownik nie istnieje!")));
    }

    private Long getLoggedOrganizerId(Authentication auth) {
        Long userId = getLoggedUserId(auth);
        return organizerService.getOrganizerIdByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Organizator nie istnieje!"));
    }

    @PostMapping("/profile")
    public ResponseEntity<String> createProfile(Authentication auth,
                                                @RequestBody OrganizerProfileDTO dto) {
        Long userId = getLoggedUserId(auth);
        organizerService.createProfile(dto, userId);

        return ResponseEntity.ok("Profil organizatora utworzony!");
    }

    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(Authentication auth,
                                                @RequestBody OrganizerProfileDTO dto) {
        Long orgId =  getLoggedOrganizerId(auth);

        organizerService.updateProfile(dto, orgId);

        return ResponseEntity.ok("Profil organizatora zaktualizowany!");
    }

    @GetMapping("/check-profile")
    public ResponseEntity<Boolean> hasProfile(Authentication auth) {
        Long userId = getLoggedUserId(auth);
        boolean exists = organizerService.isProfileExists(userId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/profile")
    public ResponseEntity<OrganizerProfileDTO> getProfile(Authentication auth) {
        Long orgId = getLoggedOrganizerId(auth);
        OrganizerProfileDTO profile = organizerService.getProfile(orgId);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/events")
    public ResponseEntity<List<EventDTO>> getAllEvents(Authentication auth) {
        Long orgId = getLoggedOrganizerId(auth);
        List<EventDTO> events = eventService.getAllByOrganizer(orgId);

        return ResponseEntity.ok(events);
    }

    @PostMapping("/events")
    public ResponseEntity<String> createEvent(Authentication auth,
                                              @RequestBody EventCreateDTO dto) {
        Long orgId = getLoggedOrganizerId(auth);
        eventService.addEvent(dto, orgId);

        return ResponseEntity.ok("Event created!");
    }

    @PutMapping("/events/{eventId}")
    public ResponseEntity<String> updateEvent(@PathVariable Long eventId,
                                              @RequestBody EventCreateDTO dto,
                                              Authentication auth) {
        Long orgId = getLoggedOrganizerId(auth);
        eventService.updateEvent(eventId, dto, orgId);

        return ResponseEntity.ok("Event updated!");
    }

    @DeleteMapping("/events/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId,
                                              Authentication auth) {
        Long orgId = getLoggedOrganizerId(auth);
        eventService.deleteEvent(eventId, orgId);

        return ResponseEntity.ok("Event deleted!");
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable Long eventId,
                                             Authentication auth) {
        Long orgId  = getLoggedOrganizerId(auth);
        EventDTO dto = eventService.getEvent(eventId, orgId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/events/{eventId}/tickets")
    public ResponseEntity<List<TicketTypeDTO>> getAllTickets(@PathVariable Long eventId,
                                                             Authentication auth) {
        Long orgId = getLoggedOrganizerId(auth);
        List<TicketTypeDTO> tickets = ticketTypeService.getAllByEvent(eventId, orgId);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/events/{eventId}/tickets")
    public ResponseEntity<String> createTicket(Authentication auth,
                                               @PathVariable Long eventId,
                                               @RequestBody TicketTypeCreateDTO dto) {
        Long orgId = getLoggedOrganizerId(auth);
        ticketTypeService.addTicketType(orgId, eventId, dto);

        return ResponseEntity.ok("Ticket created!");
    }

    @PutMapping("/events/{eventId}/tickets/{ticketId}")
    public ResponseEntity<String> updateTicket(Authentication auth,
                                               @PathVariable Long eventId,
                                               @PathVariable Long ticketId,
                                               @RequestBody TicketTypeCreateDTO dto) {
        Long orgId = getLoggedOrganizerId(auth);
        ticketTypeService.updateTicketType(orgId, eventId, ticketId, dto);
        return ResponseEntity.ok("Ticket updated!");
    }

    @DeleteMapping("/events/{eventId}/tickets/{ticketId}")
    public ResponseEntity<String> deleteTicket(Authentication auth,
                                               @PathVariable Long eventId,
                                               @PathVariable Long ticketId) {
        Long  orgId = getLoggedOrganizerId(auth);
        ticketTypeService.deleteTicketType(orgId, eventId, ticketId);
        return ResponseEntity.ok("Ticket deleted!");
    }

    @PatchMapping("/events/{eventId}/tickets/{ticketId}")
    public ResponseEntity<String> updateTicketStatus(
            Authentication auth,
            @PathVariable Long eventId,
            @PathVariable Long ticketId,
            @RequestBody boolean hidden) {
        Long  orgId = getLoggedOrganizerId(auth);
        ticketTypeService.updateTicketVisibility(orgId, eventId, ticketId, hidden);
        return ResponseEntity.ok("Widoczność biletu została zmieniona");
    }

    @PatchMapping("/bookings/{bookingId}")
    public ResponseEntity<String> updateBookingStatus(Authentication auth,
                                                      @PathVariable Long bookingId,
                                                      @RequestBody BookingStatusUpdateDTO dto) {
        Long  orgId = getLoggedOrganizerId(auth);
        organizerService.updateBookingStatus(bookingId, orgId, dto);
        return ResponseEntity.ok("Booking updated!");
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingSummaryDTO>> getAllBookings(Authentication auth) {
        Long orgId = getLoggedOrganizerId(auth);
        return ResponseEntity.ok(organizerService.getOrganizerBookings(orgId));
    }

    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<List<BookingItemDetailsDTO>> getBookingItemDetails(Authentication auth, @PathVariable Long bookingId) {
        Long orgId = getLoggedOrganizerId(auth);

        return ResponseEntity.ok(organizerService.getBookingItemDetails(orgId, bookingId));
    }
}
