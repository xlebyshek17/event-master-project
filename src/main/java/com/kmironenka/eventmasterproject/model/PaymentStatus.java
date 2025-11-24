package com.kmironenka.eventmasterproject.model;

public enum PaymentStatus {
        OCZEKUJACA("Oczekujaca"),
        ZAKONCZONA("Zakonczona"),
        BLAD("Blad");

    private String nazwaWBazie;

    PaymentStatus(String nazwaWBazie) {
        this.nazwaWBazie = nazwaWBazie;
    }

    public String getNazwaWBazie() {
        return nazwaWBazie;
    }

    public static PaymentStatus fromString(String tekst) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.nazwaWBazie.equalsIgnoreCase(tekst)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Nieznany status w bazie: " + tekst);
    }
}
