package com.example.demo.response;

import com.example.demo.entity.Booking;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookingResponse {
    private final UUID id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime bookingDate;

    public BookingResponse(Booking booking) {
        this.id = booking.getId();
        this.bookingDate = booking.getBookingDate();
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }
}