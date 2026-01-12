package com.kmironenka.eventmasterproject.model;

import lombok.Data;

@Data
public class TicketType {
    private Long ticketTypeId;
    private Long eventId;
    private String name;
    private Double price;
    private Integer totalQuantity;
    private Integer availableQuantity;
    private Boolean isHidden;

    private String eventTitle;
}
