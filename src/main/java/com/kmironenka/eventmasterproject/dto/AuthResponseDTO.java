package com.kmironenka.eventmasterproject.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private Long id;
    private String token;
    private String role;
    private String login;
}
