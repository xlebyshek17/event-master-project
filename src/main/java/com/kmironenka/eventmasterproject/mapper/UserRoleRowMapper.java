package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleRowMapper implements RowMapper<UserRole> {
    @Override
    public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserRole ur = new UserRole();
        ur.setUserId(rs.getLong("id_uzytkownika"));
        ur.setRoleId(rs.getInt("id_roli"));

        return ur;
    }
}
