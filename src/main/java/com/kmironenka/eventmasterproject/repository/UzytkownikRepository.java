package com.kmironenka.eventmasterproject.repository;

import com.kmironenka.eventmasterproject.mapper.UzytkownikRowMapper;
import com.kmironenka.eventmasterproject.model.Uzytkownik;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UzytkownikRepository {
    private final JdbcTemplate jdbcTemplate;


    public UzytkownikRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Uzytkownik> pobierzWszystkie() {
        String sql = "select * from uzytkownicy";

        return jdbcTemplate.query(sql, new UzytkownikRowMapper());
    }

    public int DodajUzytkownika(Uzytkownik u) {
        String sql = "insert into uzytkownicy (email, login, haslo_hash, imie, nazwisko, data_utworzenia) " +
                    "values (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, u.getEmail(),
                                        u.getLogin(),
                                        u.getHasloHash(),
                                        u.getImie(),
                                        u.getNazwisko(),
                                        u.getDataUtworzenia());
    }

    public int AktualizujUzytkownika(Uzytkownik u) {
        String sql = "update uzytkownicy set email = ?, login = ?, haslo_hash = ?, imie = ?, nazwisko = ? " +
                " where id_uzytkownika = ?";

        return jdbcTemplate.update(sql, u.getEmail(),
                                        u.getLogin(),
                                        u.getHasloHash(),
                                        u.getImie(),
                                        u.getNazwisko(),
                                        u.getIdUzytkownika());
    }

    public int UsunUzytkownika(Long idUzytkownika) {
        String sql = "delete from uzytkownicy where id_uzytkownika = ?";

        return jdbcTemplate.update(sql, idUzytkownika);
    }
}
