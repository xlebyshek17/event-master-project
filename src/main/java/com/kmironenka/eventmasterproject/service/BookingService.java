package com.kmironenka.eventmasterproject.service;

import com.kmironenka.eventmasterproject.dto.*;
import com.kmironenka.eventmasterproject.model.Booking;
import com.kmironenka.eventmasterproject.model.BookingItem;
import com.kmironenka.eventmasterproject.model.BookingStatus;
import com.kmironenka.eventmasterproject.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepo;
    private final TicketTypeService ticketService;

    public BookingService(BookingRepository bookingRepo, TicketTypeService ticketService) {
        this.bookingRepo = bookingRepo;
        this.ticketService = ticketService;
    }

    @Transactional
    public Long addBooking(Long userId, BookingRequestDTO booking) {
        double totalAmount = 0.0;
        List<BookingItem> items = new ArrayList<>();

        for (TicketSelection item : booking.getTickets()) {
            TicketTypeDTO ticket = ticketService.getTicketTypeById(item.getTicketTypeId());

//            if (ticket.getAvailableQuantity() < item.getQuantity()) {
//                throw new IllegalArgumentException("Zbyt mało biletów dla: " + ticket.getName());
//            }

            BookingItem bookingItem = new BookingItem();
            bookingItem.setTicketTypeId(item.getTicketTypeId());
            bookingItem.setQuantity(item.getQuantity());
            bookingItem.setPriceAtPurchase(ticket.getPrice());
            items.add(bookingItem);

            //totalAmount += (ticket.getPrice() * item.getQuantity());
        }

        Booking bookingToAdd = new Booking();
        bookingToAdd.setUserId(userId);
        bookingToAdd.setStatus(BookingStatus.OCZEKUJACA);
        bookingToAdd.setCreatedAt(OffsetDateTime.now());
        bookingToAdd.setTotalAmount(0.0);

        Long bookingId = bookingRepo.addBooking(bookingToAdd);

        for (BookingItem bookingItem : items) {
            bookingItem.setBookingId(bookingId);
            bookingRepo.addBookingItem(bookingItem);
        }

        return bookingId;
    }

    public List<BookingSummaryDTO> getUserBookings(Long userId) {
        return bookingRepo.findAllByUser(userId);
    }

    public List<BookingItemDetailsDTO> getBookingItemDetails(Long userId, Long bookingId) {
        return bookingRepo.getBookingItemDetailsForUser(userId, bookingId);
    }

    public List<SalesReportDTO> getSalesReport(Long orgId) {
        return  bookingRepo.getSalesReport(orgId);
    }

    public List<ParticipantDTO> getParticipants(Long eventId) {
        return bookingRepo.getParticipants(eventId);
    }
}
