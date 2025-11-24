package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User u = new User();
        u.setUserId(rs.getLong("id_uzytkownika"));
        u.setEmail(rs.getString("email"));
        u.setLogin(rs.getString("login"));
        u.setPasswordHash(rs.getString("haslo_hash"));
        u.setName(rs.getString("ime"));
        u.setSurname(rs.getString("nazwisko"));
        u.setCreatedAt(rs.getObject("data_utworzenia", OffsetDateTime.class));

        return u;
    }
}
