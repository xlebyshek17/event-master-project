package com.kmironenka.eventmasterproject.dto;

import lombok.Data;

@Data
public class OrganizerProfileDTO {
    private Long organizerId;
    private String organizerName;
    private String description;
    private String contactEmail;
    private String userName;
    private String userLogin;
    private Boolean isActive;
}
