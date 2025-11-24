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
        w.setStatus(rs.getObject("status", EventStatus.class));

        return w;
    }
}
