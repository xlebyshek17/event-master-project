package com.kmironenka.eventmasterproject.dto;

import lombok.Data;

@Data
public class ParticipantDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String ticketType;
    private Integer quantity;
}
