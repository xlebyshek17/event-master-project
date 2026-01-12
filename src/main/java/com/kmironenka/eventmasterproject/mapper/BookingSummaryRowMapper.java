package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.dto.BookingSummaryDTO;
import com.kmironenka.eventmasterproject.model.BookingStatus;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class BookingSummaryRowMapper implements RowMapper<BookingSummaryDTO> {

    @Override
    public BookingSummaryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookingSummaryDTO booking =  new BookingSummaryDTO();
        booking.setBookingId(rs.getLong("id_rezerwacji"));
        booking.setBookingDate(rs.getObject("data_utworzenia", OffsetDateTime.class));

        String status = rs.getString("status");

        if (status != null) {
            booking.setStatus(BookingStatus.fromString(status));
        }

        booking.setTotalAmount(rs.getDouble("kwota_calkowita"));
        booking.setUserName(rs.getString("imie_uzytkownika"));
        booking.setUserSurname(rs.getString("nazwisko_uzytkownika"));
        booking.setEventTitle(rs.getString("tytul_wydarzenia"));

        return booking;
    }
}
