package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.Uzytkownik;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class UzytkownikRowMapper implements RowMapper<Uzytkownik> {
    @Override
    public Uzytkownik mapRow(ResultSet rs, int rowNum) throws SQLException {
        Uzytkownik u = new Uzytkownik();
        u.setIdUzytkownika(rs.getLong("id_uzytkownika"));
        u.setEmail(rs.getString("email"));
        u.setLogin(rs.getString("login"));
        u.setHasloHash(rs.getString("haslo_hash"));
        u.setImie(rs.getString("ime"));
        u.setNazwisko(rs.getString("nazwisko"));
        u.setDataUtworzenia(rs.getObject("data_utworzenia", OffsetDateTime.class));

        return u;
    }
}
