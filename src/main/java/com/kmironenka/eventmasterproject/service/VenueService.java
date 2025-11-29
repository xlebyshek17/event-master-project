package com.kmironenka.eventmasterproject.service;

import com.kmironenka.eventmasterproject.dto.VenueDTO;
import com.kmironenka.eventmasterproject.model.Venue;
import com.kmironenka.eventmasterproject.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {
    private final VenueRepository repo;

    public VenueService(VenueRepository repo) {
        this.repo = repo;
    }

    public List<VenueDTO> getAllVenues() {
        return repo.getAll().stream().map(this::mapToDTO).toList();
    }

    public Optional<VenueDTO> getById(Long id) {
        return repo.getById(id).map(this::mapToDTO);
    }

    public void addVenue(VenueDTO dto) {
        if (repo.getByName(dto.getName()).isPresent()) {
            throw new IllegalArgumentException("Venue already exists");
        }

        Venue venue = new Venue();
        venue.setName(dto.getName());
        venue.setAddress(dto.getAddress());
        venue.setCity(dto.getCity());
        venue.setCapacity(dto.getCapacity());

        repo.addVenue(venue);
    }

    public void updateVenue(VenueDTO dto) {
        Venue venue = repo.getById(dto.getId()).orElse(null);
        if (venue == null) {
            throw new IllegalArgumentException("Venue does not exist");
        }

        venue.setName(dto.getName());
        venue.setAddress(dto.getAddress());
        venue.setCity(dto.getCity());
        venue.setCapacity(dto.getCapacity());

        repo.updateVenue(venue);
    }

    public void deleteVenue(Long id) {
        Venue venue = repo.getById(id).orElse(null);
        if (venue == null) {
            throw new IllegalArgumentException("Venue does not exist");
        }
        repo.deleteVenue(id);
    }

    private VenueDTO mapToDTO(Venue venue) {
        VenueDTO dto = new VenueDTO();
        dto.setId(venue.getVenueId());
        dto.setName(venue.getName());
        dto.setAddress(venue.getAddress());
        dto.setCity(venue.getCity());
        dto.setCapacity(venue.getCapacity());
        return dto;
    }
}
