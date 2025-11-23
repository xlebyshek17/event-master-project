package com.kmironenka.eventmasterproject.model;

import lombok.Data;

@Data
public class Organizator {
    private Long idOrganizatora;
    private Long idUzytkownika;
    private String nazwaOrganizacji;
    private String opis;
    private String emailKontaktowy;
}
