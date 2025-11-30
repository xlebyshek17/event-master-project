package com.kmironenka.eventmasterproject.controller;

import com.kmironenka.eventmasterproject.dto.OrganizerProfileDTO;
import com.kmironenka.eventmasterproject.service.OrganizerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizer")
public class OrganizerController {
    private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
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
}
