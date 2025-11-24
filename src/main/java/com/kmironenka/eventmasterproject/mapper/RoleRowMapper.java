package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role r = new Role();
        r.setRoleId(rs.getInt("id_roli"));
        r.setRoleName(rs.getString("nazwa_roli"));

        return r;
    }
}
