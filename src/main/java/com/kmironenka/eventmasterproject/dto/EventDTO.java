package com.kmironenka.eventmasterproject.dto;

import com.kmironenka.eventmasterproject.model.EventStatus;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class EventDTO {
    private Long eventId;
    private String title;
    private String description;
    private String imageUrl;

    private OffsetDateTime startTime;
    private OffsetDateTime endTime;

    private Long venueId;
    private Integer categoryId;

    private String venueName;
    private String city;
    private String address;

    private String organizerName;
    private String categoryName;

    private Double minPrice;

    private EventStatus status;
}
