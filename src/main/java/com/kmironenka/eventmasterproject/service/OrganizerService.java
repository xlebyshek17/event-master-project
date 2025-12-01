package com.kmironenka.eventmasterproject.service;

import com.kmironenka.eventmasterproject.dto.OrganizerProfileDTO;
import com.kmironenka.eventmasterproject.model.Organizer;
import com.kmironenka.eventmasterproject.repository.OrganizerRepository;
import com.kmironenka.eventmasterproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService {
    private final OrganizerRepository organizerRepo;
    private final UserRepository userRepo;

    public OrganizerService(OrganizerRepository organizerRepo, UserRepository userRepo) {
        this.organizerRepo = organizerRepo;
        this.userRepo = userRepo;
    }

    public void createProfile(OrganizerProfileDTO dto, Long userId) {
        String role = userRepo.getUserRoleName(userId);

        if (!"ORGANIZER".equals(role)) {
            throw new IllegalArgumentException("Tylko organizator może utworzyć profil firmy.");
        }

        if(isProfileExists(userId)) {
            throw new IllegalArgumentException("Użytkownik już posiada profil firmy");
        }

        Organizer org = new Organizer();
        org.setUserId(userId);
        org.setName(dto.getOrganizerName());
        org.setDescription(dto.getDescription());
        org.setContactEmail(dto.getContactEmail());

        organizerRepo.addOrganizer(org);
    }

    public void updateProfile(OrganizerProfileDTO dto, Long orgId) {
        Organizer org = new Organizer();

        org.setOrganizerId(orgId);
        org.setName(dto.getOrganizerName());
        org.setDescription(dto.getDescription());
        org.setContactEmail(dto.getContactEmail());

        int rowsAffected = organizerRepo.updateOrganizer(org);

        if (rowsAffected == 0) {
            throw new IllegalArgumentException("Organization does not exist!");
        }
    }

    public boolean isProfileExists(Long userId) {
        return organizerRepo.getOrganizerIdByUserId(userId).isPresent();
    }
}
