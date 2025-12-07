package com.kmironenka.eventmasterproject.model;

import lombok.Getter;

@Getter
public enum EventStatus {
    SZKIC("Szkic"),
    OPUBLIKOWANE("Opublikowane"),
    ANULOWANE("Anulowane");

    private final String nameInDB;

    EventStatus(String nameInDB) {
        this.nameInDB = nameInDB;
    }

    // Metoda pomocnicza: Zamienia napis z bazy na Enum
    public static EventStatus fromString(String tekst) {
        for (EventStatus status : EventStatus.values()) {
            if (status.nameInDB.equalsIgnoreCase(tekst)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Nieznany status w bazie: " + tekst);
    }
}