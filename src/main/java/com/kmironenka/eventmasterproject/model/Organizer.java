package com.kmironenka.eventmasterproject.model;

import lombok.Data;

@Data
public class Organizer {
    private Long organizerId;
    private Long userId;
    private String name;
    private String description;
    private String contactEmail;
    private String userName;
    private String userSurname;
    private String userLogin;
    private Boolean isActive;
}
