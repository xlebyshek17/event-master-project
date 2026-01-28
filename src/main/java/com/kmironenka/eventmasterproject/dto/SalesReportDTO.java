package com.kmironenka.eventmasterproject.dto;

import lombok.Data;

@Data
public class SalesReportDTO {
    private Long eventId;
    private String eventTitle;
    private Integer ticketsSold;
    private Double totalRevenue;
}
