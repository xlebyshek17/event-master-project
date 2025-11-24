package com.kmironenka.eventmasterproject.model;

public enum EventStatus {
    SZKIC("Szkic"),
    OPUBLIKOWANE("Opublikowane"),
    ANULOWANE("Anulowane");

    private final String nazwaWBazie;

    EventStatus(String nazwaWBazie) {
        this.nazwaWBazie = nazwaWBazie;
    }

    public String getNazwaWBazie() {
        return nazwaWBazie;
    }

    // Metoda pomocnicza: Zamienia napis z bazy na Enum
    public static EventStatus fromString(String tekst) {
        for (EventStatus status : EventStatus.values()) {
            if (status.nazwaWBazie.equalsIgnoreCase(tekst)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Nieznany status w bazie: " + tekst);
    }
}