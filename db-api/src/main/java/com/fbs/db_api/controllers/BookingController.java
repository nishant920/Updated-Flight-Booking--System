package com.fbs.db_api.controllers;

import com.fbs.db_api.model.Booking;
import com.fbs.db_api.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/db/booking")
public class BookingController {

    BookingRepository bookingRepository;

    @Autowired
    public BookingController(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    @PostMapping("/create")
    public Booking createBooking(@RequestBody Booking booking){
        return bookingRepository.save(booking);
    }
}
