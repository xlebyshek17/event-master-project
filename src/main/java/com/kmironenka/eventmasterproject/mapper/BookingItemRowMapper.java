package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.BookingItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingItemRowMapper implements RowMapper<BookingItem> {
    @Override
    public BookingItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookingItem pr = new BookingItem();
        pr.setBookingItemId(rs.getLong("id_pozycji"));
        pr.setBookingId(rs.getLong("id_rezerwacji"));
        pr.setTicketTypeId(rs.getLong("id_typu_biletu"));
        pr.setQuantity(rs.getInt("ilosc"));
        pr.setPriceAtPurchase(rs.getDouble("cena_w_chwili_zakupu"));

        return pr;
    }
}
