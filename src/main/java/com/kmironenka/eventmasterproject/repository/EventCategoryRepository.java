package com.kmironenka.eventmasterproject.repository;

import com.kmironenka.eventmasterproject.mapper.EventCategoryRowMapper;
import com.kmironenka.eventmasterproject.model.EventCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventCategoryRepository {
    private final JdbcTemplate jdbcTemplate;

    public EventCategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<EventCategory> getAll() {
        String sql = "select * from kategorie_wydarzen";
        return jdbcTemplate.query(sql, new EventCategoryRowMapper());
    }

    public Optional<EventCategory> getById(Integer id) {
        String sql = "select * from kategorie_wydarzen where id_kategorii = ?";
        return jdbcTemplate.query(sql, new EventCategoryRowMapper(), id).stream().findFirst();
    }

    public Optional<EventCategory> getByName(String name) {
        String sql = "select * from kategorie_wydarzen where nazwa = ?";
        return jdbcTemplate.query(sql, new EventCategoryRowMapper(), name).stream().findFirst();
    }

    public void addEventCategory(EventCategory category) {
        String sql = "insert into kategorie_wydarzen (nazwa) values (?)";
        jdbcTemplate.update(sql, category.getName());
    }

    public int updateEventCategory(EventCategory category) {
        String sql = "update kategorie_wydarzen set nazwa = ? where id_kategorii = ?";
        return jdbcTemplate.update(sql, category.getName(), category.getCategoryId());
    }

    public int deleteEventCategory(Integer id) {
        String sql = "delete from kategorie_wydarzen where id_kategorii = ?";
        return jdbcTemplate.update(sql, id);
    }
}
