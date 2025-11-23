package com.kmironenka.eventmasterproject.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data // generuje gettery, settery, toString
public class Uzytkownik {
    private Long idUzytkownika;
    private String email;
    private String login;
    private String hasloHash;
    private String imie;
    private String nazwisko;
    private OffsetDateTime dataUtworzenia;
}
