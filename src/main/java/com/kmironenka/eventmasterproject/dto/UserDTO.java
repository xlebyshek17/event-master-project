package com.kmironenka.eventmasterproject.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String login;
    private String name;
    private String surname;
    private String role;
}
