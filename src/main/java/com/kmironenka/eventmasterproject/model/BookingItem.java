package com.kmironenka.eventmasterproject.model;

import lombok.Data;

@Data
public class BookingItem {
    private Long bookingItemId;
    private Long bookingId;
    private Long ticketTypeId;
    private Integer quantity;
    private Double priceAtPurchase;
}
