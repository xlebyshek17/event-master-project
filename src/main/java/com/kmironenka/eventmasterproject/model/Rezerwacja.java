package com.kmironenka.eventmasterproject.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Rezerwacja {
    private Long idRezerwacji;
    private Long idUzytkownika;
    private StatusRezerwacji status;
    private OffsetDateTime dataUtworzenia;
    private Double kwotaCalkowita;
}
