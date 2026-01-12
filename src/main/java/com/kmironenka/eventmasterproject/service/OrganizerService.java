package com.kmironenka.eventmasterproject.service;

import com.kmironenka.eventmasterproject.dto.BookingItemDetailsDTO;
import com.kmironenka.eventmasterproject.dto.BookingStatusUpdateDTO;
import com.kmironenka.eventmasterproject.dto.BookingSummaryDTO;
import com.kmironenka.eventmasterproject.dto.OrganizerProfileDTO;
import com.kmironenka.eventmasterproject.model.Organizer;
import com.kmironenka.eventmasterproject.repository.BookingRepository;
import com.kmironenka.eventmasterproject.repository.OrganizerRepository;
import com.kmironenka.eventmasterproject.repository.RoleRepository;
import com.kmironenka.eventmasterproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizerService {
    private final OrganizerRepository organizerRepo;
    private final UserRepository userRepo;
    private final BookingRepository bookingRepo;
    private final RoleRepository roleRepository;

    public OrganizerService(OrganizerRepository organizerRepo,
                            UserRepository userRepo,
                            BookingRepository bookingRepo, RoleRepository roleRepository) {
        this.organizerRepo = organizerRepo;
        this.userRepo = userRepo;
        this.bookingRepo = bookingRepo;
        this.roleRepository = roleRepository;
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

    public OrganizerProfileDTO getProfile(Long orgId) {
        Organizer org = organizerRepo.getById(orgId)
                .orElseThrow(() -> new IllegalArgumentException("Profil organizatora nie istnieje!"));
        return mapToDTO(org);
    }

    public boolean isProfileExists(Long userId) {
        return organizerRepo.getOrganizerIdByUserId(userId).isPresent();
    }

    public Optional<Long> getOrganizerIdByUserId(Long userId) {
        return organizerRepo.getOrganizerIdByUserId(userId);
    }

    @Transactional
    public void updateBookingStatus(Long bookingId, Long orgId, BookingStatusUpdateDTO status) {
        boolean isOwner = bookingRepo.isBookingRelatedToOrganizator(bookingId, orgId);
        if (!isOwner) {
            throw new IllegalArgumentException("Nie masz uprawnień do zarządzania tą rezerwacją!");
        }

        int affected = bookingRepo.updateBookingStatus(bookingId, status.getStatus());

        if (affected == 0) {
            throw new IllegalArgumentException("Rezerwacji nie istniej!");
        }
    }

    @Transactional
    public void deleteOrganizerAndDowngradeUser(Long organizerId) {
        Long userId = organizerRepo.getUserIdByOrganizerId(organizerId).orElseThrow(() -> new IllegalArgumentException("Nie istnieje!"));

        int affected = organizerRepo.deleteById(organizerId);
        if (affected == 0) {
            throw new IllegalArgumentException("Organizatora nie istnieje!");
        }

        if (userId != null) {
            int oldRoleId = roleRepository.getRoleId("ORGANIZER");
            roleRepository.deleteUserRole(userId, oldRoleId);
            int newRoleId = roleRepository.getRoleId("USER");
            userRepo.setRoleToUser(userId, newRoleId);
        }
    }

    public List<BookingSummaryDTO> getOrganizerBookings(Long orgId) {
        return bookingRepo.findAllByOrganizer(orgId);
    }

    public List<BookingItemDetailsDTO> getBookingItemDetails(Long orgId, Long bookingId) {
        return bookingRepo.getBookingItemDetailsForOrganizers(orgId, bookingId);
    }

    public List<OrganizerProfileDTO> getAllOrganizerProfiles() {
        List<Organizer> orgs = organizerRepo.getAll();
        return orgs.stream().map(this::mapToDTO).toList();
    }

    @Transactional
    public void updateOrganizerStatus(Long orgId, boolean status) {
        int affected = organizerRepo.updateOrganizerStatus(orgId, status);

        System.out.println("status: "+ status);

        if (affected == 0) {
            throw new IllegalArgumentException("Profil organizatora nie istnieje!");
        }

        if (!status) {
            organizerRepo.cancelEvent(orgId);
        }
    }

    private OrganizerProfileDTO mapToDTO(Organizer org) {
        OrganizerProfileDTO dto = new OrganizerProfileDTO();
        dto.setOrganizerId(org.getOrganizerId());
        dto.setOrganizerName(org.getName());
        dto.setContactEmail(org.getContactEmail());
        dto.setDescription(org.getDescription());
        dto.setUserLogin(org.getUserLogin());
        dto.setUserName(org.getUserName() + " " + org.getUserSurname());
        dto.setIsActive(org.getIsActive());

        return dto;
    }
}
