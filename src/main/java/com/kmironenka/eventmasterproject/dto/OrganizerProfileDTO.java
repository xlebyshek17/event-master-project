package com.kmironenka.eventmasterproject.dto;

import lombok.Data;

@Data
public class OrganizerProfileDTO {
    private Long organizerId;
    private String organizerName;
    private String description;
    private String contactEmail;
}
