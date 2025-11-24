package com.kmironenka.eventmasterproject.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Review {
    private Long reviewId;
    private Long eventId;
    private Long userId;
    private Integer rating;
    private String comment;
    private OffsetDateTime createdAt;
}
