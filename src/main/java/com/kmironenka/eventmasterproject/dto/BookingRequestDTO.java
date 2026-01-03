package com.kmironenka.eventmasterproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequestDTO {
    private List<TicketSelection> tickets;
}

