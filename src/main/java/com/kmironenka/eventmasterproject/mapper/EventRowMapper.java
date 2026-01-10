package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.EventStatus;
import com.kmironenka.eventmasterproject.model.Event;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class EventRowMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        Event w = new Event();
        w.setEventId(rs.getLong("id_wydarzenia"));
        w.setTitle(rs.getString("tytul"));
        w.setDescription(rs.getString("opis"));
        w.setStartTime(rs.getObject("data_rozpoczecia", OffsetDateTime.class));
        w.setEndTime(rs.getObject("data_zakonczenia", OffsetDateTime.class));
        w.setOrganizerId(rs.getLong("id_organizatora"));
        w.setVenueId(rs.getLong("id_miejsca"));
        w.setCategoryId(rs.getInt("id_kategorii"));
        w.setImageUrl(rs.getString("zdjecie_url"));

        String eventStatus = rs.getString("status");
        if (eventStatus != null) {
            w.setStatus(EventStatus.fromString(eventStatus));
        }

        try {
            w.setVenueName(rs.getString("nazwa_miejsca"));         // Musi pasować do AS nazwa_miejsca
            w.setCategoryName(rs.getString("nazwa_kategorii"));    // Musi pasować do AS nazwa_kategorii
            w.setOrganizerName(rs.getString("nazwa_organizatora"));// Musi pasować do AS nazwa_organizatora
            w.setCity(rs.getString("miasto_miejsca"));
            w.setAddress(rs.getString("adres"));
            w.setMinPrice(rs.getDouble("cena_od"));
        } catch (SQLException e) {
            // Ignorujemy błąd, jeśli kolumny nie ma (np. w zapytaniu findById bez joinów)
            // Dzięki temu mapper jest uniwersalny!
        }

        return w;
    }
}
