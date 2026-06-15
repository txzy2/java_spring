package com.example.demo.controller;

import com.example.demo.service.BookingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

//    public ResponseEntity<String> createBooking(@RequestBody CreateBookingRequest request) {
//
//    }
}
