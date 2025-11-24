package com.kmironenka.eventmasterproject.model;

import lombok.Data;

@Data
public class Venue {
    private Long venueId;
    private String name;
    private String address;
    private String city;
    private Integer capacity;
}
