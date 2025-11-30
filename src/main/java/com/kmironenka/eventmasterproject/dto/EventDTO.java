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
    private String organizerName;
    private String venueName;
    private String categoryName;
    private EventStatus status;
}
