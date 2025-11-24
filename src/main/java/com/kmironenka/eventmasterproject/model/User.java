package com.kmironenka.eventmasterproject.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data // generuje gettery, settery, toString
public class User {
    private Long userId;
    private String email;
    private String login;
    private String passwordHash;
    private String name;
    private String surname;
    private OffsetDateTime createdAt;
}
