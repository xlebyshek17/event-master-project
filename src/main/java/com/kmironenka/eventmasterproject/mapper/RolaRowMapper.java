package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.Rola;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RolaRowMapper implements RowMapper<Rola> {

    @Override
    public Rola mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rola r = new Rola();
        r.setIdRoli(rs.getInt("id_roli"));
        r.setNazwaRoli(rs.getString("nazwa_roli"));

        return r;
    }
}
