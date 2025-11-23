package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.PozycjaRezerwacji;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PozycjaRezerwacjiRowMapper implements RowMapper<PozycjaRezerwacji> {
    @Override
    public PozycjaRezerwacji mapRow(ResultSet rs, int rowNum) throws SQLException {
        PozycjaRezerwacji pr = new PozycjaRezerwacji();
        pr.setIdPozycji(rs.getLong("id_pozycji"));
        pr.setIdRezerwacji(rs.getLong("id_rezerwacji"));
        pr.setIdTypuBiletu(rs.getLong("id_typu_biletu"));
        pr.setIlosc(rs.getInt("ilosc"));
        pr.setCenaWChwiliZakupu(rs.getDouble("cena_w_chwili_zakupu"));

        return pr;
    }
}
