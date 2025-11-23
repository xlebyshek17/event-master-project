package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.Opinia;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class OpiniaRowMapper implements RowMapper<Opinia> {
    @Override
    public Opinia mapRow(ResultSet rs, int rowNum) throws SQLException {
        Opinia o = new Opinia();
        o.setIdOpinii(rs.getLong("id_opinii"));
        o.setIdWydarzenia(rs.getLong("id_wydarzenia"));
        o.setIdUzytkownika(rs.getLong("id_uzytkownika"));
        o.setOcena(rs.getInt("ocena"));
        o.setKomentarz(rs.getString("komentarz"));
        o.setDataDodania(rs.getObject("data_dodania", OffsetDateTime.class));

        return o;
    }
}
