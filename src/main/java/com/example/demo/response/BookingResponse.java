package com.example.demo.response;

import com.example.demo.entity.Booking;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@JsonPropertyOrder({"id", "booking_date", "description", "doctor"})
public class BookingResponse {
    private UUID id;

    @JsonProperty("booking_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bookingDate;

    private String description;

    private DoctorResponse doctor;

    public BookingResponse() {
    }

    public BookingResponse(Booking booking) {
        this.id = booking.getId();
        this.bookingDate = booking.getBookingDate();
        this.description = booking.getDescription();
        this.doctor = new DoctorResponse(booking.getDoctor());
    }
}