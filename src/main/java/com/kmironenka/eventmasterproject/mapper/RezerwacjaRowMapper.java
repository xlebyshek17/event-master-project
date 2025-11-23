package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.Rezerwacja;
import com.kmironenka.eventmasterproject.model.StatusRezerwacji;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class RezerwacjaRowMapper implements RowMapper<Rezerwacja> {

    @Override
    public Rezerwacja mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rezerwacja r  = new Rezerwacja();
        r.setIdRezerwacji(rs.getLong("id_rezerwacji"));
        r.setIdUzytkownika(rs.getLong("id_uzytkownika"));
        r.setStatus(rs.getObject("status", StatusRezerwacji.class));
        r.setDataUtworzenia(rs.getObject("data_utworzenia", OffsetDateTime.class));
        r.setKwotaCalkowita(rs.getDouble("kwota_calkowita"));

        return r;
    }
}
