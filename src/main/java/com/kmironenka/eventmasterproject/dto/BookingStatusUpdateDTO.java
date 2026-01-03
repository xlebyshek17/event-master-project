package com.kmironenka.eventmasterproject.dto;

import com.kmironenka.eventmasterproject.model.BookingStatus;
import lombok.Data;

@Data
public class BookingStatusUpdateDTO {
    private BookingStatus status;
}
