package com.kmironenka.eventmasterproject.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Wydarzenie {
    private Long idWydarzenia;
    private String tytul;
    private String opis;
    private OffsetDateTime dataRozpoczecia;
    private OffsetDateTime dataZakonczenia;
    private Long idOrganizatora;
    private Long idMiejsca;
    private Integer idKategorii;
    private StatusWydarzenia status;
}
