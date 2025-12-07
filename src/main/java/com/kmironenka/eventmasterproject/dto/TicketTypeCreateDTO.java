package com.kmironenka.eventmasterproject.dto;

import lombok.Data;

@Data
public class TicketTypeCreateDTO {
    private Long ticketTypeId;
    private Long eventId;
    private String name;
    private Double price;
    private Integer totalQuantity;
}
