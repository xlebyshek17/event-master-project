package com.kmironenka.eventmasterproject.controller;

import com.kmironenka.eventmasterproject.dto.BookingItemDetailsDTO;
import com.kmironenka.eventmasterproject.dto.BookingRequestDTO;
import com.kmironenka.eventmasterproject.dto.BookingSummaryDTO;
import com.kmironenka.eventmasterproject.service.BookingService;
import com.kmironenka.eventmasterproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final UserService userService;

    public BookingController(BookingService service,  UserService userService) {
        this.bookingService = service;
        this.userService = userService;
    }

    private Long getLoggedUserId(Authentication auth) {
        String login  = auth.getName();
        return userService.getIdByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException(("Użytkownik nie istnieje!")));
    }

    @PostMapping
    public ResponseEntity<String> createBooking(Authentication auth, @RequestBody BookingRequestDTO booking) {
        Long userId = getLoggedUserId(auth);

        Long bookingId = bookingService.addBooking(userId, booking);
        return ResponseEntity.ok("Rezerwacja utworzona! ID: " + bookingId);
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<List<BookingSummaryDTO>> getMyBookings(Authentication auth) {
        Long userId = getLoggedUserId(auth);

        return ResponseEntity.ok(bookingService.getUserBookings(userId));
    }

    @GetMapping("/my-bookings/{bookingId}")
    public ResponseEntity<List<BookingItemDetailsDTO>> getBookingItemDetails(Authentication auth, @PathVariable Long bookingId) {
        Long userId = getLoggedUserId(auth);

        return ResponseEntity.ok(bookingService.getBookingItemDetails(userId, bookingId));
    }
}
