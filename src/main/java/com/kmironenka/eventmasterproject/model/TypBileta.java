package com.kmironenka.eventmasterproject.model;

import lombok.Data;

@Data
public class TypBileta {
    private Long idTypuBiletu;
    private Long idWydarzenia;
    private String nazwa;
    private Double cena;
    private Integer pulaCalkowita;
    private Integer iloscDostepna;
}
