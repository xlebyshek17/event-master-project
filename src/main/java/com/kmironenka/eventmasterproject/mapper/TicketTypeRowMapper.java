package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.TicketType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketTypeRowMapper implements RowMapper<TicketType> {
    @Override
    public TicketType mapRow(ResultSet rs, int rowNum) throws SQLException {
        TicketType tb = new TicketType();
        tb.setTicketTypeId(rs.getLong("id_typu_biletu"));
        tb.setEventId(rs.getLong("id_wydarzenia"));
        tb.setName(rs.getString("nazwa"));
        tb.setPrice(rs.getDouble(("cena")));
        tb.setTotalQuantity(rs.getInt("pula_calkowita"));
        tb.setAvailableQuantity(rs.getInt("ilosc_dostepna"));

        return tb;
    }
}
