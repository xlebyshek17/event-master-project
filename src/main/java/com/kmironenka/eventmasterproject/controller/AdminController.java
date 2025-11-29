package com.kmironenka.eventmasterproject.controller;

import com.kmironenka.eventmasterproject.dto.EventCategoryDTO;
import com.kmironenka.eventmasterproject.dto.UserDTO;
import com.kmironenka.eventmasterproject.dto.VenueDTO;
import com.kmironenka.eventmasterproject.service.EventCategoryService;
import com.kmironenka.eventmasterproject.service.UserService;
import com.kmironenka.eventmasterproject.service.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;
    private final EventCategoryService categoryService;
    private final VenueService venueService;

    public AdminController(UserService userService, EventCategoryService categoryService, VenueService venueService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.venueService = venueService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.ok("User deleted successfully with id " + id);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        Optional<UserDTO> u = userService.getUserById(id);

        return u.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/event-categories")
    public ResponseEntity<List<EventCategoryDTO>> getEventCategories() {
        List<EventCategoryDTO> categories = categoryService.getAllCategories();

        return ResponseEntity.ok(categories);
    }

    @PostMapping("/event-categories")
    public ResponseEntity<String> addEventCategory(@RequestBody EventCategoryDTO categoryDTO) {
        categoryService.addCategory(categoryDTO);

        return ResponseEntity.ok("Category added successfully");
    }

    @PutMapping("/event-categories/{id}")
    public ResponseEntity<String> updateEventCategory(@PathVariable Integer id, @RequestBody EventCategoryDTO categoryDTO) {
        categoryDTO.setId(id);

        categoryService.updateCategory(categoryDTO);

        return ResponseEntity.ok("Category updated successfully");
    }

    @DeleteMapping("/event-categories/{id}")
    public ResponseEntity<String> deleteEventCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);

        return ResponseEntity.ok("Category deleted successfully");
    }

    @GetMapping("/event-categories/{id}")
    public ResponseEntity<EventCategoryDTO> getEventCategory(@PathVariable Integer id) {
        Optional<EventCategoryDTO> categoryDTO = categoryService.getById(id);

        return categoryDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/venues")
    public ResponseEntity<List<VenueDTO>> getVenues() {
        List<VenueDTO> venues = venueService.getAllVenues();
        return ResponseEntity.ok(venues);
    }

    @PostMapping("/venues")
    public ResponseEntity<String> addVenue(@RequestBody VenueDTO venueDTO) {
        venueService.addVenue(venueDTO);
        return ResponseEntity.ok("Venue added successfully");
    }

    @GetMapping("/venues/{id}")
    public ResponseEntity<VenueDTO> getVenue(@PathVariable Long id) {
        Optional<VenueDTO> venueDTO = venueService.getById(id);
        return venueDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/venues/{id}")
    public ResponseEntity<String> updateVenue(@PathVariable Long id, @RequestBody VenueDTO venueDTO) {
        venueDTO.setId(id);
        venueService.updateVenue(venueDTO);
        return ResponseEntity.ok("Venue updated successfully");
    }

    @DeleteMapping("/venues/{id}")
    public ResponseEntity<String> deleteVenue(@PathVariable Long id) {
        venueService.deleteVenue(id);
        return ResponseEntity.ok("Venue deleted successfully");
    }
}
