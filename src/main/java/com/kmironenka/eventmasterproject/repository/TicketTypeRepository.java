package com.kmironenka.eventmasterproject.repository;

import com.kmironenka.eventmasterproject.mapper.TicketTypeRowMapper;
import com.kmironenka.eventmasterproject.model.TicketType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketTypeRepository {
    private final JdbcTemplate jdbcTemplate;

    public TicketTypeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TicketType> getAllByEventId(Long eventId) {
        String sql = "select *, w.tytul as tytul_wydarzenia from typy_biletow t join wydarzenia w on t.id_wydarzenia = w.id_wydarzenia where t.id_wydarzenia = ?";
        return jdbcTemplate.query(sql, new TicketTypeRowMapper(), eventId);
    }

    public void addTicketType(TicketType ticketType) {
        String sql = "insert into typy_biletow (id_wydarzenia, nazwa, cena, pula_calkowita, ilosc_dostepna) " +
                "values(?,?,?,?,?)";

        jdbcTemplate.update(sql,
                ticketType.getEventId(),
                ticketType.getName(),
                ticketType.getPrice(),
                ticketType.getTotalQuantity(),
                ticketType.getAvailableQuantity());
    }

    public int updateTicketType(TicketType ticketType) {
        String sql = "update typy_biletow set nazwa = ?, cena = ?, pula_calkowita = ? " +
                "where id_typu_biletu = ? and id_wydarzenia = ?";

        return jdbcTemplate.update(sql,
                ticketType.getName(),
                ticketType.getPrice(),
                ticketType.getTotalQuantity(),
                ticketType.getTicketTypeId(),
                ticketType.getEventId());
    }

    public int deleteTicketType(Long ticketTypeId, Long eventId) {
        String sql = "delete from typy_biletow where id_typu_biletu = ? and id_wydarzenia = ?";
        return jdbcTemplate.update(sql, ticketTypeId, eventId);
    }
}
