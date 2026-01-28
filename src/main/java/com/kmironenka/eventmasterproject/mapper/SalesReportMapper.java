package com.kmironenka.eventmasterproject.mapper;

import com.kmironenka.eventmasterproject.dto.SalesReportDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesReportMapper implements RowMapper<SalesReportDTO> {
    @Override
    public SalesReportDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        SalesReportDTO dto = new SalesReportDTO();
        dto.setEventId(rs.getLong("id_wydarzenia"));
        dto.setEventTitle(rs.getString("tytul"));
        dto.setTicketsSold(rs.getInt("sprzedane_bilety"));
        dto.setTotalRevenue(rs.getDouble("przychod_calkowity"));
        return dto;
    }
}
