package com.kmironenka.eventmasterproject.service;

import com.kmironenka.eventmasterproject.dto.TicketTypeCreateDTO;
import com.kmironenka.eventmasterproject.dto.TicketTypeDTO;
import com.kmironenka.eventmasterproject.model.TicketType;
import com.kmironenka.eventmasterproject.repository.EventRepository;
import com.kmironenka.eventmasterproject.repository.TicketTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketTypeService {
    private final TicketTypeRepository ticketTypeRepo;
    private final EventRepository eventRepo;

    public TicketTypeService(TicketTypeRepository ticketTypeRepo, EventRepository eventRepo) {
        this.ticketTypeRepo = ticketTypeRepo;
        this.eventRepo = eventRepo;
    }

    public List<TicketTypeDTO> getAllByEvent(Long eventId, Long orgId) {
        boolean isOwner = eventRepo.isEventOwner(eventId, orgId);
        if (!isOwner) {
            throw new IllegalArgumentException("Event with id " + eventId + " does not belong to organizer " + orgId);
        }
        return ticketTypeRepo.getAllByEventId(eventId).stream().map(this::mapToDTO).toList();
    }

    public List<TicketTypeDTO> getTicketsForPublicEvent(Long eventId) {
        return ticketTypeRepo.getAllByEventId(eventId).stream().map(this::mapToDTO).toList();
    }

    public void addTicketType(Long orgId, Long eventId, TicketTypeCreateDTO dto) {
        boolean isOwner = eventRepo.isEventOwner(eventId, orgId);
        if (!isOwner) {
            throw new IllegalArgumentException("Event with id " + eventId + " does not belong to organizer " + orgId);
        }

        TicketType t = new TicketType();

        mapFromDTO(dto, t);
        t.setEventId(eventId);
        t.setAvailableQuantity(dto.getTotalQuantity());

        ticketTypeRepo.addTicketType(t);
    }

    public void updateTicketType(Long orgId, Long eventId, Long ticketId, TicketTypeCreateDTO dto) {
        boolean isOwner = eventRepo.isEventOwner(eventId, orgId);
        if (!isOwner) {
            throw new IllegalArgumentException("Event with id " + eventId + " does not belong to organizer " + orgId);
        }

        TicketType t = new TicketType();

        mapFromDTO(dto, t);
        t.setTicketTypeId(ticketId);
        t.setEventId(eventId);

        int affectedRows = ticketTypeRepo.updateTicketType(t);

        if (affectedRows == 0) {
            throw new IllegalArgumentException("Ticket type does not exist!");
        }
    }

    public void deleteTicketType(Long orgId, Long eventId, Long ticketId) {
        boolean isOwner = eventRepo.isEventOwner(eventId, orgId);
        if (!isOwner) {
            throw new IllegalArgumentException("Event with id " + eventId + " does not belong to organizer " + orgId);
        }

        int affected = ticketTypeRepo.deleteTicketType(ticketId, eventId);
        if (affected == 0) {
            throw new IllegalArgumentException("Ticket type does not exist!");
        }
    }

    public void updateTicketVisibility(Long orgId, Long eventId, Long ticketId, boolean hidden) {
        boolean isOwner = eventRepo.isEventOwner(eventId, orgId);
        if (!isOwner) {
            throw new IllegalArgumentException("Event with id " + eventId + " does not belong to organizer " + orgId);
        }

        int affected = ticketTypeRepo.updateTicketVisibility(eventId, ticketId, hidden);
        if (affected == 0) {
            throw new IllegalArgumentException("Bilet nie istnieje!");
        }
    }

    public TicketTypeDTO getTicketTypeById(Long ticketId) {
        TicketType ticket = ticketTypeRepo.getTicketTypeById(ticketId).orElseThrow(() -> new IllegalArgumentException("Bileta nie istnieje"));
        return mapToDTO(ticket);
    }

    private TicketTypeDTO mapToDTO(TicketType ticketType) {
        TicketTypeDTO dto = new TicketTypeDTO();
        dto.setTicketTypeId(ticketType.getTicketTypeId());
        dto.setEventTitle(ticketType.getEventTitle());
        dto.setName(ticketType.getName());
        dto.setPrice(ticketType.getPrice());
        dto.setTotalQuantity(ticketType.getTotalQuantity());
        dto.setAvailableQuantity(ticketType.getAvailableQuantity());
        dto.setIsHidden(ticketType.getIsHidden());

        return dto;
    }

    public void mapFromDTO(TicketTypeCreateDTO dto, TicketType t) {
        t.setName(dto.getName());
        t.setPrice(dto.getPrice());
        t.setTotalQuantity(dto.getTotalQuantity());
    }
}
