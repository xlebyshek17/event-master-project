package com.kmironenka.eventmasterproject.model;

public enum StatusPlatnosci {
        OCZEKUJACA("Oczekujaca"),
        ZAKONCZONA("Zakonczona"),
        BLAD("Blad");

    private String nazwaWBazie;

    StatusPlatnosci(String nazwaWBazie) {
        this.nazwaWBazie = nazwaWBazie;
    }

    public String getNazwaWBazie() {
        return nazwaWBazie;
    }

    public static StatusPlatnosci fromString(String tekst) {
        for (StatusPlatnosci status : StatusPlatnosci.values()) {
            if (status.nazwaWBazie.equalsIgnoreCase(tekst)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Nieznany status w bazie: " + tekst);
    }
}
