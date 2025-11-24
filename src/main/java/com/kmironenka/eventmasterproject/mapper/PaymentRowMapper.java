package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.model.Payment;
import com.kmironenka.eventmasterproject.model.PaymentStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class PaymentRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment p = new Payment();
        p.setPaymentId(rs.getLong("id_platnosci"));
        p.setBookingId(rs.getLong("id_rezerwacji"));
        p.setAmount(rs.getDouble("kwota"));
        p.setPaymentMethod(rs.getString("metoda_platnosci"));
        p.setStatus(rs.getObject("status", PaymentStatus.class));
        p.setTransactionId(rs.getInt("id_tranzakcji_zewnetrznej"));
        p.setPaymentDate(rs.getObject("data_utworzenia", OffsetDateTime.class));

        return p;
    }
}
