package com.kmironenka.eventmasterproject.repository;

import com.kmironenka.eventmasterproject.mapper.UserRowMapper;
import com.kmironenka.eventmasterproject.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Optional<User> getById(Long userId) {
        String sql = "select * from uzytkownicy where id_uzytkownika = ?";
        return jdbcTemplate.query(sql, new UserRowMapper(), userId).stream().findFirst();
    }

    public Optional<User> getByLogin(String login) {
        String sql = "select * from uzytkownicy where login = ?";
        return jdbcTemplate.query(sql, new UserRowMapper(), login).stream().findFirst();
    }

    public Optional<User> getByEmail(String email) {
        String sql = "select * from uzytkownicy where email = ?";
        return jdbcTemplate.query(sql, new UserRowMapper(), email).stream().findFirst();
    }

    // Add user and Return ID
    public Long addUser(User u) {
        String sql = "insert into uzytkownicy (email, login, haslo_hash, imie, nazwisko, data_utworzenia) " +
                    "values (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection-> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_uzytkownika"});
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getLogin());
            ps.setString(3, u.getPasswordHash());
            ps.setString(4, u.getName());
            ps.setString(5, u.getSurname());
            ps.setObject(6, u.getCreatedAt());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void updateUser(User u) {
        String sql = "update uzytkownicy set email = ?, login = ?, haslo_hash = ?, imie = ?, nazwisko = ? " +
                " where id_uzytkownika = ?";

        jdbcTemplate.update(sql, u.getEmail(),
                                        u.getLogin(),
                                        u.getPasswordHash(),
                                        u.getName(),
                                        u.getSurname(),
                                        u.getUserId());
    }

    public void deleteUser(Long userId) {
        String sql = "delete from uzytkownicy where id_uzytkownika = ?";

        jdbcTemplate.update(sql, userId);
    }

    public void setRoleToUser(Long userId, Integer roleId) {
        String sql = "insert into uzytkownicy_role (id_uzytkownika, id_roli) values (?, ?)";
        jdbcTemplate.update(sql, userId, roleId);
    }

    public String getUserRoleName(Long userId) {
        String sql = "select r.nazwa_roli " +
                "from role r " +
                "join uzytkownicy_role ur " +
                "on r.id_roli = ur.id_roli " +
                "where ur.id_uzytkownika = ?";
        return jdbcTemplate.queryForObject(sql, String.class, userId);
    }
}
