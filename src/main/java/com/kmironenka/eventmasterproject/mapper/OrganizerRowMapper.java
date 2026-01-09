package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.Organizer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganizerRowMapper implements RowMapper<Organizer> {
    @Override
    public Organizer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Organizer o = new Organizer();
        o.setOrganizerId(rs.getLong("id_organizatora"));
        o.setUserId(rs.getLong("id_uzytkownika"));
        o.setName(rs.getString("nazwa_organizacji"));
        o.setDescription(rs.getString("opis"));
        o.setContactEmail(rs.getString("email_kontaktowy"));
        o.setUserName(rs.getString("imie"));
        o.setUserSurname(rs.getString("nazwisko"));
        o.setUserLogin(rs.getString("login"));

        return o;
    }
}
