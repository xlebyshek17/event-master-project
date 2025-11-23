package com.kmironenka.eventmasterproject.model;

import lombok.Data;

@Data
public class Miejsce {
    private Long idMiejsca;
    private String nazwa;
    private String adres;
    private String miasto;
    private Integer pojemnosc;
}
