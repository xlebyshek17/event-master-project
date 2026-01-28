package com.kmironenka.eventmasterproject.repository;

import com.kmironenka.eventmasterproject.mapper.EventRowMapper;
import com.kmironenka.eventmasterproject.model.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class EventRepository {
    private final JdbcTemplate jdbcTemplate;

    public EventRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String SELECT_FROM_VIEW = "select * from v_public_events";

    public List<Event> getAll(Long orgId) {
        String sql = SELECT_FROM_VIEW + " where id_organizatora = ?";
        return jdbcTemplate.query(sql, new EventRowMapper(), orgId);
    }

    public List<Event> getPublishedEvents() {
        String sql = SELECT_FROM_VIEW + " where status = 'Opublikowane'";
        return jdbcTemplate.query(sql, new EventRowMapper());
    }

    public Long addEvent(Event event) {
        String sql = "INSERT INTO wydarzenia (tytul, opis, data_rozpoczecia, data_zakonczenia, id_organizatora, id_miejsca, id_kategorii, status, zdjecie_url) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_wydarzenia"});
            ps.setString(1, event.getTitle());
            ps.setString(2, event.getDescription());
            ps.setObject(3, event.getStartTime());
            ps.setObject(4, event.getEndTime());
            ps.setLong(5, event.getOrganizerId());
            ps.setLong(6, event.getVenueId());
            ps.setInt(7, event.getCategoryId());
            // Enum na Stringa
            ps.setString(8, event.getStatus().getNameInDB());
            ps.setString(9, event.getImageUrl());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public int updateEvent(Event event) {
        String sql = "update wydarzenia set tytul = ?, opis = ?, data_rozpoczecia = ?," +
                " data_zakonczenia = ?, id_organizatora = ?, id_miejsca = ?, id_kategorii = ?, status = ?, zdjecie_url = ? " +
                " where id_wydarzenia = ? and id_organizatora = ?";

        return jdbcTemplate.update(sql, event.getTitle(), event.getDescription(), event.getStartTime(),
                event.getEndTime(), event.getOrganizerId(), event.getVenueId(),
                event.getCategoryId(), event.getStatus().getNameInDB(), event.getImageUrl(), event.getEventId(), event.getOrganizerId());
    }

    public int deleteEvent(Long eventId, Long organizerId) {
        String sql = "delete from wydarzenia where id_wydarzenia = ? and id_organizatora = ?";
        return jdbcTemplate.update(sql, eventId, organizerId);
    }

    public Optional<Event> getEventById(Long eventId) {
        String sql = SELECT_FROM_VIEW + " where id_wydarzenia = ?";
        return jdbcTemplate.query(sql, new EventRowMapper(), eventId).stream().findFirst();
    }

    public Optional<Event> getPublishedEventById(Long eventId) {
        String sql = SELECT_FROM_VIEW + " where id_wydarzenia = ? and status = 'Opublikowane'";
        return jdbcTemplate.query(sql, new EventRowMapper(), eventId).stream().findFirst();
    }

    public boolean existsByDetails(String title, Long venueId, OffsetDateTime startTime) {
        String sql = "select count(*) from wydarzenia where tytul = ? and id_miejsca = ? and data_rozpoczecia = ?";

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, title, venueId, startTime);
        return count != null && count > 0;
    }

    public boolean isEventOwner(Long eventId, Long organizerId) {
        String sql = "select count(*) from wydarzenia where id_wydarzenia = ? and id_organizatora = ?";
        Integer count =  jdbcTemplate.queryForObject(sql, Integer.class, eventId, organizerId);
        return count != null && count > 0;
    }

    public List<Event> getSimilarEvents(Long eventId, int categoryId) {
        String sql = SELECT_FROM_VIEW +
                     " where id_wydarzenia != ? and " +
                     "      id_kategorii = ? and " +
                     "      status = 'Opublikowane' " +
                     "limit 6";
        return jdbcTemplate.query(sql, new EventRowMapper(), eventId, categoryId);
    }

}
