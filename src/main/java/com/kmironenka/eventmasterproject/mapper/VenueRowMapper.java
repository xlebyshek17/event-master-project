package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.Venue;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VenueRowMapper implements RowMapper<Venue> {
    @Override
    public Venue mapRow(ResultSet rs, int rowNum) throws SQLException {
        Venue m = new Venue();
        m.setVenueId(rs.getLong("id_miejsca"));
        m.setName(rs.getString("nazwa"));
        m.setAddress(rs.getString("adres"));
        m.setCity(rs.getString("miasto"));
        m.setCapacity(rs.getInt("pojemnosc"));

        return m;
    }
}
