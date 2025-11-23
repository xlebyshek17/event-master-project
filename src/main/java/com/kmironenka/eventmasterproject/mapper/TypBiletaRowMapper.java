package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.TypBileta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TypBiletaRowMapper implements RowMapper<TypBileta> {
    @Override
    public TypBileta mapRow(ResultSet rs, int rowNum) throws SQLException {
        TypBileta tb = new TypBileta();
        tb.setIdTypuBiletu(rs.getLong("id_typu_biletu"));
        tb.setIdWydarzenia(rs.getLong("id_wydarzenia"));
        tb.setNazwa(rs.getString("nazwa"));
        tb.setCena(rs.getDouble(("cena")));
        tb.setPulaCalkowita(rs.getInt("pula_calkowita"));
        tb.setIloscDostepna(rs.getInt("ilosc_dostepna"));

        return tb;
    }
}
