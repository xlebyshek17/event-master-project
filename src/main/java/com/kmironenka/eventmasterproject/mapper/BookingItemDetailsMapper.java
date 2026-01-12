package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.dto.BookingItemDetailsDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class BookingItemDetailsMapper implements RowMapper<BookingItemDetailsDTO>  {
    @Nullable
    @Override
    public BookingItemDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookingItemDetailsDTO pr = new BookingItemDetailsDTO();
        pr.setEventTitle(rs.getString("eventTitle"));
        pr.setEventDate(rs.getObject("eventDate", OffsetDateTime.class));
        pr.setCity(rs.getString("city"));
        pr.setVenueName(rs.getString("venueName"));
        pr.setTicketTypeName(rs.getString("ticketTypeName"));
        pr.setQuantity(rs.getInt("quantity"));
        pr.setPriceAtPurchase(rs.getDouble("priceAtPurchase"));
        pr.setSubtotal(rs.getDouble("subtotal"));

        return pr;
    }
}
