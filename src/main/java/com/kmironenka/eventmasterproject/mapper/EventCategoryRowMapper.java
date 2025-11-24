package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.EventCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventCategoryRowMapper implements RowMapper<EventCategory> {
    @Override
    public EventCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        EventCategory kw = new EventCategory();
        kw.setCategoryId(rs.getInt("id_kategorii"));
        kw.setName(rs.getString("nazwa"));

        return kw;
    }
}
