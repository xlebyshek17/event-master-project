package com.kmironenka.eventmasterproject.dto;

import com.kmironenka.eventmasterproject.model.EventStatus;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class EventDTO {
    private Long eventId;
    private String title;
    private String description;

    private OffsetDateTime startTime;
    private OffsetDateTime endTime;

    private String venueName;
    private String city;

    private String organizerName;
    private String categoryName;

    private Double minPrice;

    private EventStatus status;
}
