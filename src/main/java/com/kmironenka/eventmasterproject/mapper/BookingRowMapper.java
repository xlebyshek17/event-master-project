package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.Booking;
import com.kmironenka.eventmasterproject.model.BookingStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class BookingRowMapper implements RowMapper<Booking> {

    @Override
    public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
        Booking r  = new Booking();
        r.setBookingId(rs.getLong("id_rezerwacji"));
        r.setUserId(rs.getLong("id_uzytkownika"));
        r.setStatus(rs.getObject("status", BookingStatus.class));
        r.setCreatedAt(rs.getObject("data_utworzenia", OffsetDateTime.class));
        r.setTotalAmount(rs.getDouble("kwota_calkowita"));

        return r;
    }
}
