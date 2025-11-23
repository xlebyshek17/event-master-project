package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.Platnosc;
import com.kmironenka.eventmasterproject.model.StatusPlatnosci;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class PlatnoscRowMapper implements RowMapper<Platnosc> {
    @Override
    public Platnosc mapRow(ResultSet rs, int rowNum) throws SQLException {
        Platnosc p = new Platnosc();
        p.setIdPlatnosci(rs.getLong("id_platnosci"));
        p.setIdRezerwacji(rs.getLong("id_rezerwacji"));
        p.setKwota(rs.getDouble("kwota"));
        p.setMetodaPlatnosci(rs.getString("metoda_platnosci"));
        p.setStatus(rs.getObject("status", StatusPlatnosci.class));
        p.setIdTranzakcjiZewnetrznej(rs.getInt("id_tranzakcji_zewnetrznej"));
        p.setDataPlatnosci(rs.getObject("data_utworzenia", OffsetDateTime.class));

        return p;
    }
}
