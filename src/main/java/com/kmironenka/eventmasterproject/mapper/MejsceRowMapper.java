package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.Miejsce;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MejsceRowMapper implements RowMapper<Miejsce> {
    @Override
    public Miejsce mapRow(ResultSet rs, int rowNum) throws SQLException {
        Miejsce m = new Miejsce();
        m.setIdMiejsca(rs.getLong("id_miejsca"));
        m.setNazwa(rs.getString("nazwa"));
        m.setAdres(rs.getString("adres"));
        m.setMiasto(rs.getString("miasto"));
        m.setPojemnosc(rs.getInt("pojemnosc"));

        return m;
    }
}
