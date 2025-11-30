package com.kmironenka.eventmasterproject.repository;

import com.kmironenka.eventmasterproject.dto.VenueDTO;
import com.kmironenka.eventmasterproject.mapper.VenueRowMapper;
import com.kmironenka.eventmasterproject.model.Venue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VenueRepository {
    private final JdbcTemplate jdbcTemplate;

    public VenueRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Venue> getAll() {
        String sql = "select * from miejsca";
        return jdbcTemplate.query(sql, new VenueRowMapper());
    }

    public Optional<Venue> getByName(String name) {
        String sql = "select * from miejsca where nazwa = ?";
        return jdbcTemplate.query(sql, new VenueRowMapper(), name).stream().findFirst();
    }

    public Optional<Venue> getById(Long id) {
        String sql = "select * from miejsca where id_miejsca = ?";
        return jdbcTemplate.query(sql, new VenueRowMapper(), id).stream().findFirst();
    }

    public void addVenue(Venue venue) {
        String sql = "insert into miejsca(nazwa, adres, miasto, pojemnosc) values(?,?,?,?)";
        jdbcTemplate.update(sql, venue.getName(),
                                venue.getAddress(),
                                venue.getCity(),
                                venue.getCapacity());
    }

    public void updateVenue(Venue venue) {
        String sql = "update miejsca set nazwa = ?, adres = ?, miasto = ?, pojemnosc = ? where id_miejsca = ?";
        jdbcTemplate.update(sql, venue.getName(),
                                venue.getAddress(),
                                venue.getCity(),
                                venue.getCapacity(),
                                venue.getVenueId());
    }

    public void deleteVenue(Long id) {
        String sql = "delete from miejsca where id_miejsca = ?";
        jdbcTemplate.update(sql, id);
    }
}
