package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.Organizator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganizatorRowMapper implements RowMapper<Organizator> {
    @Override
    public Organizator mapRow(ResultSet rs, int rowNum) throws SQLException {
        Organizator o = new Organizator();
        o.setIdOrganizatora(rs.getLong("id_organizatora"));
        o.setIdUzytkownika(rs.getLong("id_uzytkownika"));
        o.setNazwaOrganizacji(rs.getString("nazwa_organizacji"));
        o.setOpis(rs.getString("opis"));
        o.setEmailKontaktowy(rs.getString("email_kontaktowy"));

        return o;
    }
}
