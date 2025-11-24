package com.kmironenka.eventmasterproject.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Event {
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
