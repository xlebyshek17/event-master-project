package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.Uzytkownik;
import com.kmironenka.eventmasterproject.model.UzytkownikRola;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UzytkownikRolaRowMapper  implements RowMapper<UzytkownikRola> {
    @Override
    public UzytkownikRola mapRow(ResultSet rs, int rowNum) throws SQLException {
        UzytkownikRola ur = new UzytkownikRola();
        ur.setIdUzytkownika(rs.getLong("id_uzytkownika"));
        ur.setIdRoli(rs.getInt("id_roli"));

        return ur;
    }
}
