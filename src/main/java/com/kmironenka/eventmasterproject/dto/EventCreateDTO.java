package com.kmironenka.eventmasterproject.dto;

import com.kmironenka.eventmasterproject.model.EventStatus;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class EventCreateDTO {
    private Long eventId;
    private String title;
    private String description;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private Long organizerId;
    private Long venueId;
    private Integer categoryId;
    private EventStatus status;
}
