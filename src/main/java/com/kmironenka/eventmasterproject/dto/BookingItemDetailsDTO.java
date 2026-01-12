package com.kmironenka.eventmasterproject.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class BookingItemDetailsDTO {
    private String eventTitle;
    private OffsetDateTime eventDate;
    private String venueName;
    private String city;
    private String ticketTypeName;
    private Integer quantity;
    private Double priceAtPurchase;
    private Double subtotal;
}
