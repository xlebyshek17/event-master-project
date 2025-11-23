package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.KategoriaWydarzenia;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KategoriaWydarzeniaRowMapper implements RowMapper<KategoriaWydarzenia> {
    @Override
    public KategoriaWydarzenia mapRow(ResultSet rs, int rowNum) throws SQLException {
        KategoriaWydarzenia kw = new KategoriaWydarzenia();
        kw.setIdKategorii(rs.getInt("idKategorii"));
        kw.setNazwa(rs.getString("nazwa"));

        return kw;
    }
}
