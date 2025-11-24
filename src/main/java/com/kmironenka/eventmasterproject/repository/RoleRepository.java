package com.kmironenka.eventmasterproject.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository {
    private final JdbcTemplate jdbcTemplate;

    public RoleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getRoleId(String roleName) {
        String sql = "select id_roli from role where nazwa_roli = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, roleName);
    }

    public List<String> getAll() {
        String sql = "select nazwa_roli from role";
        return jdbcTemplate.queryForList(sql, String.class);
    }
}
