package com.kmironenka.eventmasterproject.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Opinia {
    private Long idOpinii;
    private Long idWydarzenia;
    private Long idUzytkownika;
    private Integer ocena;
    private String komentarz;
    private OffsetDateTime dataDodania;
}
