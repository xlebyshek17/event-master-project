package com.kmironenka.eventmasterproject.model;

public enum BookingStatus {
    OCZEKUJACA("Oczekujaca"),
    ZATWIERDZONA("Zatwierdzona"),
    ANULOWANA("Anulowana");

    private final String nazwaWBazie;

    BookingStatus(String nazwaWBaze) {
        this.nazwaWBazie = nazwaWBaze;
    }

    public String getNazwaWBazie() {
        return nazwaWBazie;
    }

    public static BookingStatus fromString(String tekst) {
        for (BookingStatus status : BookingStatus.values()) {
            if (status.nazwaWBazie.equalsIgnoreCase(tekst)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Nieznany status w bazie: " + tekst);
    }
}
