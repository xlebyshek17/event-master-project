package com.kmironenka.eventmasterproject.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Payment {
    private Long paymentId;
    private Long bookingId;
    private Double amount;
    private String paymentMethod;
    private PaymentStatus status;
    private Integer transactionId;
    private OffsetDateTime paymentDate;
}
