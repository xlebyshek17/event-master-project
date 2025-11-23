package com.kmironenka.eventmasterproject.model;

public enum StatusWydarzenia {
    SZKIC("Szkic"),
    OPUBLIKOWANE("Opublikowane"),
    ANULOWANE("Anulowane");

    private final String nazwaWBazie;

    StatusWydarzenia(String nazwaWBazie) {
        this.nazwaWBazie = nazwaWBazie;
    }

    public String getNazwaWBazie() {
        return nazwaWBazie;
    }

    // Metoda pomocnicza: Zamienia napis z bazy na Enum
    public static StatusWydarzenia fromString(String tekst) {
        for (StatusWydarzenia status : StatusWydarzenia.values()) {
            if (status.nazwaWBazie.equalsIgnoreCase(tekst)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Nieznany status w bazie: " + tekst);
    }
}