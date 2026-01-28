package com.kmironenka.eventmasterproject.repository;

import com.kmironenka.eventmasterproject.dto.BookingItemDetailsDTO;
import com.kmironenka.eventmasterproject.dto.BookingSummaryDTO;
import com.kmironenka.eventmasterproject.dto.ParticipantDTO;
import com.kmironenka.eventmasterproject.dto.SalesReportDTO;
import com.kmironenka.eventmasterproject.mapper.BookingItemDetailsMapper;
import com.kmironenka.eventmasterproject.mapper.BookingSummaryRowMapper;
import com.kmironenka.eventmasterproject.mapper.ParticipantMapper;
import com.kmironenka.eventmasterproject.mapper.SalesReportMapper;
import com.kmironenka.eventmasterproject.model.Booking;
import com.kmironenka.eventmasterproject.model.BookingItem;
import com.kmironenka.eventmasterproject.model.BookingStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
public class BookingRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long addBooking(Booking booking) {
        String sql = "insert into rezerwacje (id_uzytkownika, status, data_utworzenia, kwota_calkowita) " +
                "values (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_rezerwacji"});
            ps.setLong(1, booking.getUserId());
            //Enum na string
            ps.setString(2,booking.getStatus().getNazwaWBazie());
            ps.setObject(3, booking.getCreatedAt());
            ps.setDouble(4, booking.getTotalAmount());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void addBookingItem(BookingItem item) {
        String sql = "insert into pozycje_rezerwacji (id_rezerwacji, id_typu_biletu, ilosc, cena_w_chwili_zakupu) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, item.getBookingId(), item.getTicketTypeId(), item.getQuantity(), item.getPriceAtPurchase());
    }

    public int updateBookingStatus(Long bookingId, BookingStatus status) {
        String sql = "update rezerwacje set status = ? where id_rezerwacji = ?";
        return jdbcTemplate.update(sql, status.getNazwaWBazie(), bookingId);
    }

    public boolean isBookingRelatedToOrganizator(Long bookingId, Long organizatorId) {
        String sql = "select count(*) " +
                "from rezerwacje r " +
                "join pozycje_rezerwacji pr  on r.id_rezerwacji = pr.id_rezerwacji " +
                "join typy_biletow tb on pr.id_typu_biletu = tb.id_typu_biletu " +
                "join wydarzenia w on tb.id_wydarzenia = w.id_wydarzenia " +
                "where r.id_rezerwacji = ? and w.id_organizatora = ?";

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, bookingId, organizatorId);
        return count != null && count > 0;
    }

    // 1. DLA ORGANIZATORA (Wszystkie rezerwacje na jego wydarzenia)
    public List<BookingSummaryDTO> findAllByOrganizer(Long orgId) {
        String sql = "select * from v_bookings where id_organizatora = ? order by v_bookings.data_utworzenia desc";

        return jdbcTemplate.query(sql, new BookingSummaryRowMapper(), orgId);
    }

    // 2. DLA UŻYTKOWNIKA (Moje zakupy)
    public List<BookingSummaryDTO> findAllByUser(Long userId) {
        String sql = "select * from v_bookings where id_uzytkownika = ? order by v_bookings.data_utworzenia desc";


        return jdbcTemplate.query(sql, new BookingSummaryRowMapper(), userId);
    }

    public List<BookingItemDetailsDTO> getBookingItemDetailsForUser(Long userId, Long bookingId) {
        String sql = """
                select * from v_booking_details where id_rezerwacji = ? and id_uzytkownika = ?""";
        return jdbcTemplate.query(sql, new BookingItemDetailsMapper(), bookingId, userId);
    }

    public List<BookingItemDetailsDTO> getBookingItemDetailsForOrganizers(Long orgId, Long bookingId) {
        String sql = """
                SELECT * from v_booking_details where id_rezerwacji = ? and id_organizatora = ?""";
        return jdbcTemplate.query(sql, new BookingItemDetailsMapper(), bookingId, orgId);
    }

    public List<SalesReportDTO> getSalesReport(Long orgId) {
        String sql = "SELECT id_wydarzenia, tytul, sprzedane_bilety, przychod_calkowity FROM v_sales_report WHERE id_organizatora = ?";
        return jdbcTemplate.query(sql, new SalesReportMapper(), orgId);
    }

    public List<ParticipantDTO> getParticipants(Long eventId) {
        String sql = """
        SELECT imie, nazwisko, email, typ_biletu, ilosc 
        FROM v_event_participants 
        WHERE id_wydarzenia = ? AND status_rezerwacji = 'Zatwierdzona' 
        ORDER BY nazwisko ASC
    """;
        return jdbcTemplate.query(sql, new ParticipantMapper(), eventId);
    }
}
