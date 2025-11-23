package com.kmironenka.eventmasterproject.model;

public enum StatusRezerwacji {
    OCZEKUJACA("Oczekujaca"),
    ZATWIERDZONA("Zatwierdzona"),
    ANULOWANA("Anulowana");

    private final String nazwaWBazie;

    StatusRezerwacji(String nazwaWBaze) {
        this.nazwaWBazie = nazwaWBaze;
    }

    public String getNazwaWBazie() {
        return nazwaWBazie;
    }

    public static StatusRezerwacji fromString(String tekst) {
        for (StatusRezerwacji status : StatusRezerwacji.values()) {
            if (status.nazwaWBazie.equalsIgnoreCase(tekst)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Nieznany status w bazie: " + tekst);
    }
}
