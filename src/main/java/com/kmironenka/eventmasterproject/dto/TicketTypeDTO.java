package com.kmironenka.eventmasterproject.dto;

import lombok.Data;

@Data
public class TicketTypeDTO {
    private Long ticketTypeId;
    private String eventTitle;
    private String name;
    private Double price;
    private Integer totalQuantity;
    private Integer availableQuantity;
}
