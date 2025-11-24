package com.kmironenka.eventmasterproject.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Booking {
    private Long bookingId;
    private Long userId;
    private BookingStatus status;
    private OffsetDateTime createdAt;
    private Double totalAmount;
}
