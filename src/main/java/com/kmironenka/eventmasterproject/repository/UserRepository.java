package com.kmironenka.eventmasterproject.repository;

import com.kmironenka.eventmasterproject.mapper.UserRowMapper;
import com.kmironenka.eventmasterproject.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;


    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAll() {
        String sql = "select * from uzytkownicy";

        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public User getById(Integer userId) {
        String sql = "select * from uzytkownicy where id_uzytkownika = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int addUser(User u) {
        String sql = "insert into uzytkownicy (email, login, haslo_hash, imie, nazwisko, data_utworzenia) " +
                    "values (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, u.getEmail(),
                                        u.getLogin(),
                                        u.getPaswwordHash(),
                                        u.getName(),
                                        u.getSurname(),
                                        u.getCreatedAt());
    }

    public int updateUser(User u) {
        String sql = "update uzytkownicy set email = ?, login = ?, haslo_hash = ?, imie = ?, nazwisko = ? " +
                " where id_uzytkownika = ?";

        return jdbcTemplate.update(sql, u.getEmail(),
                                        u.getLogin(),
                                        u.getPaswwordHash(),
                                        u.getName(),
                                        u.getSurname(),
                                        u.getUserId());
    }

    public int deleteUser(Long userId) {
        String sql = "delete from uzytkownicy where id_uzytkownika = ?";

        return jdbcTemplate.update(sql, userId);
    }
}
