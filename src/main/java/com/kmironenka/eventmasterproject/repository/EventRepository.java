package com.kmironenka.eventmasterproject.repository;

import com.kmironenka.eventmasterproject.mapper.EventRowMapper;
import com.kmironenka.eventmasterproject.model.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class EventRepository {
    private final JdbcTemplate jdbcTemplate;

    public EventRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String SELECT_WITH_JOINS = """
        SELECT e.*,
               m.nazwa AS nazwa_miejsca, 
               k.nazwa AS nazwa_kategorii,
               o.nazwa_organizacji AS nazwa_organizatora
        FROM wydarzenia e
        LEFT JOIN miejsca m ON e.id_miejsca = m.id_miejsca
        LEFT JOIN kategorie_wydarzen k ON e.id_kategorii = k.id_kategorii
        LEFT JOIN organizatorzy o ON e.id_organizatora = o.id_organizatora
    """;

    public List<Event> getAll(Long orgId) {
        String sql = SELECT_WITH_JOINS + " where e.id_organizatora = ?";
        return jdbcTemplate.query(sql, new EventRowMapper(), orgId);
    }

    public Long addEvent(Event event) {
        String sql = "INSERT INTO wydarzenia (tytul, opis, data_rozpoczecia, data_zakonczenia, id_organizatora, id_miejsca, id_kategorii, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
            ps.setString(8, event.getStatus().toString());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public int updateEvent(Event event) {
        String sql = "update wydarzenia set tytul = ?, opis = ?, data_rozpoczecia = ?," +
                " data_zakonczenia = ?, id_organizatora = ?, id_miejsca = ?, id_kategorii = ?, status = ?" +
                " where id_wydarzenia = ? and id_organizatora = ?";

        return jdbcTemplate.update(sql, event.getTitle(), event.getDescription(), event.getStartTime(),
                event.getEndTime(), event.getOrganizerId(), event.getVenueId(),
                event.getCategoryId(), event.getStatus(), event.getEventId(), event.getOrganizerId());
    }

    public int deleteEvent(Long eventId, Long organizerId) {
        String sql = "delete from wydarzenia where id_wydarzenia = ? and id_organizatora = ?";
        return jdbcTemplate.update(sql, eventId, organizerId);
    }

    public Optional<Event> getEventById(Long eventId) {
        String sql = SELECT_WITH_JOINS + " where id_wydarzenia = ?";
        return jdbcTemplate.query(sql, new EventRowMapper(), eventId).stream().findFirst();
    }
}
