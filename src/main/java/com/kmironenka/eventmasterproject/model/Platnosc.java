package com.kmironenka.eventmasterproject.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Platnosc {
    private Long idPlatnosci;
    private Long idRezerwacji;
    private Double kwota;
    private String metodaPlatnosci;
    private StatusRezerwacji status;
    private Integer idTranzakcjiZewnetrznej;
    private OffsetDateTime dataPlatnosci;
}
