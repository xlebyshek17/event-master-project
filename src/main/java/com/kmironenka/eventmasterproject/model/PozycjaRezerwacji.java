package com.kmironenka.eventmasterproject.model;

import lombok.Data;

@Data
public class PozycjaRezerwacji {
    private Long idPozycji;
    private Long idRezerwacji;
    private Long idTypuBiletu;
    private Integer ilosc;
    private Double cenaWChwiliZakupu;
}
