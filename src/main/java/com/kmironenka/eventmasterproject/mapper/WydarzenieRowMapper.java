package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.StatusWydarzenia;
import com.kmironenka.eventmasterproject.model.Wydarzenie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class WydarzenieRowMapper implements RowMapper<Wydarzenie> {

    @Override
    public Wydarzenie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Wydarzenie w = new Wydarzenie();
        w.setIdWydarzenia(rs.getLong("id_wydarzenia"));
        w.setTytul(rs.getString("tytul"));
        w.setOpis(rs.getString("opis"));
        w.setDataRozpoczecia(rs.getObject("data_rozpoczecia", OffsetDateTime.class));
        w.setDataZakonczenia(rs.getObject("data_zakonczenia", OffsetDateTime.class));
        w.setIdOrganizatora(rs.getLong("id_organizatora"));
        w.setIdMiejsca(rs.getLong("id_miejsca"));
        w.setIdKategorii(rs.getInt("id_kategorii"));
        w.setStatus(rs.getObject("status", StatusWydarzenia.class));

        return w;
    }
}
