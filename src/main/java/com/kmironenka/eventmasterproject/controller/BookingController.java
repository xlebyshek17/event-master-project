package com.kmironenka.eventmasterproject.controller;

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

    @PostMapping
    public ResponseEntity<String> createBooking(Authentication auth, @RequestBody BookingRequestDTO booking) {
        String login = auth.getName();
        Long userId = userService.getIdByLogin(login).orElseThrow(() -> new IllegalArgumentException("User does not exist!"));

        Long bookingId = bookingService.addBooking(userId, booking);
        return ResponseEntity.ok("Rezerwacja utworzona! ID: " + bookingId);
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<List<BookingSummaryDTO>> getMyBookings(Authentication auth) {
        // Wyciągamy ID z tokena (tak jak robiliśmy przy kupowaniu)
        String login = auth.getName();
        Long userId = userService.getIdByLogin(login).orElseThrow(() -> new IllegalArgumentException("User does not exist!"));

        return ResponseEntity.ok(bookingService.getUserBookings(userId));
    }
}
