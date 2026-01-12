package com.kmironenka.eventmasterproject.dto;

import com.kmironenka.eventmasterproject.model.BookingStatus;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class BookingSummaryDTO {
    private Long bookingId;
    private OffsetDateTime bookingDate;
    private BookingStatus status;
    private Double totalAmount;

    private String eventTitle; // Kluczowe: Na co to bilet?
    private String userName;  // Kluczowe dla Organizatora: Kto kupił?
    private String userSurname;
}
