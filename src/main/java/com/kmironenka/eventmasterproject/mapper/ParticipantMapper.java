package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.dto.ParticipantDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipantMapper implements RowMapper<ParticipantDTO> {
    @Override
    public ParticipantDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ParticipantDTO p = new ParticipantDTO();
        p.setFirstName(rs.getString("imie"));
        p.setLastName(rs.getString("nazwisko"));
        p.setEmail(rs.getString("email"));
        p.setTicketType(rs.getString("typ_biletu"));
        p.setQuantity(rs.getInt("ilosc"));
        return p;
    }
}
