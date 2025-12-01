package com.kmironenka.eventmasterproject.service;

import com.kmironenka.eventmasterproject.dto.OrganizerProfileDTO;
import com.kmironenka.eventmasterproject.model.Organizer;
import com.kmironenka.eventmasterproject.repository.OrganizerRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService {
    private final OrganizerRepository organizerRepo;

    public OrganizerService(OrganizerRepository organizerRepo) {
        this.organizerRepo = organizerRepo;
    }

    public void createProfile(OrganizerProfileDTO dto, Long userId) {
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
}
