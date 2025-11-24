package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class ReviewRowMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        Review o = new Review();
        o.setReviewId(rs.getLong("id_opinii"));
        o.setEventId(rs.getLong("id_wydarzenia"));
        o.setUserId(rs.getLong("id_uzytkownika"));
        o.setRating(rs.getInt("ocena"));
        o.setComment(rs.getString("komentarz"));
        o.setCreatedAt(rs.getObject("data_dodania", OffsetDateTime.class));

        return o;
    }
}
