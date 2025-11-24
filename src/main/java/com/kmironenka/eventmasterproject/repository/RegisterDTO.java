package com.kmironenka.eventmasterproject.repository;

import lombok.Data;

@Data
public class RegisterDTO {
    private String email;
    private String login;
    private String password; // Czyste hasło (plain text)
    private String name;
    private String surname;
    private String role; // USER , ORGANIZER, ADMIN
}
