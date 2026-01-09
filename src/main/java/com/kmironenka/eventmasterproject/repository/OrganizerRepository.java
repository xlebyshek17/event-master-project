package com.kmironenka.eventmasterproject.repository;

import com.kmironenka.eventmasterproject.mapper.OrganizerRowMapper;
import com.kmironenka.eventmasterproject.model.Organizer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrganizerRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrganizerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addOrganizer(Organizer o) {
        String sql = "insert into organizatorzy (id_uzytkownika, nazwa_organizacji, opis, email_kontaktowy) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, o.getUserId(), o.getName(), o.getDescription(), o.getContactEmail());
    }

    public Optional<Long> getOrganizerIdByUserId(Long userId) {
        String sql = "select id_organizatora from organizatorzy where id_uzytkownika = ?";
        try {
            Long id = jdbcTemplate.queryForObject(sql, Long.class, userId);
            return Optional.ofNullable(id);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<Long> getUserIdByOrganizerId(Long organizerId) {
        String sql = "select id_uzytkownika from organizatorzy where id_organizatora = ?";
        try {
            Long id = jdbcTemplate.queryForObject(sql, Long.class, organizerId);
            return Optional.ofNullable(id);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public int updateOrganizer(Organizer o) {
        String sql = "update organizatorzy set nazwa_organizacji = ?, opis = ?, email_kontaktowy = ? where id_organizatora = ?";
        return jdbcTemplate.update(sql, o.getName(), o.getDescription(), o.getContactEmail(), o.getOrganizerId());
    }

    public Optional<Organizer> getById(Long organizerId) {
        String sql = "select * from organizatorzy where id_organizatora = ?";
        return jdbcTemplate.query(sql, new OrganizerRowMapper(), organizerId).stream().findFirst();
    }

    public List<Organizer> getAll() {
        String sql = "select *, u.login as login, u.imie as imie, u.nazwisko as nazwisko f" +
                     "rom organizatorzy o join uzytkownicy u on o.id_uzytkownika = u.id_uzytkownika";
        return jdbcTemplate.query(sql, new OrganizerRowMapper());
    }

    public int deleteById(Long organizerId) {
        String sql = "delete from organizatorzy where id_organizatora = ?";
        return jdbcTemplate.update(sql, organizerId);
    }
}
