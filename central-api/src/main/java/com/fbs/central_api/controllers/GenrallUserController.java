package com.fbs.central_api.controllers;

import com.fbs.central_api.dto.*;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.services.BookingService;
import com.fbs.central_api.services.FlightService;
import com.fbs.central_api.services.UserService;
import com.fbs.central_api.utility.AuthUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/central/user")
public class GenrallUserController {

    UserService userService;
    AuthUtility authUtility;
    FlightService flightService;
    BookingService bookingService;

    public GenrallUserController(UserService userService, AuthUtility authUtility, FlightService flightService,
                                 BookingService bookingService){
        this.userService=userService;
        this.authUtility=authUtility;
        this.flightService=flightService;
        this.bookingService=bookingService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto){
        String token = userService.isValidCredentials(loginDto.getEmail(), loginDto.getPassword());
        LoginResponseDto responseDto = userService.login(loginDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody CustomerRegistrationDto customerRegistrationDto){
        CustomerRegistrationResponseDto responseUser = userService.registerUser(customerRegistrationDto);
        return new ResponseEntity(responseUser, HttpStatus.CREATED);
    }

    @GetMapping("/flight/search")
    public AllFlightSearchResponseDto search(@RequestParam String sourceAirport,
                                             @RequestParam String destinationAirport,
                                             @RequestParam String datetime){
        AllFlightSearchResponseDto allFlightSearchResponseDto = flightService.searchFlight(sourceAirport, destinationAirport, datetime);
        return allFlightSearchResponseDto;
    }

    @PostMapping("/booking/create")
    public ResponseEntity createBooking(@RequestBody BookingRequestDto bookingRequestDto,
                                        @RequestHeader String Authorization){
        BookingResponseDto bookingResponseDto = bookingService.createBooking(bookingRequestDto, Authorization);
        return new ResponseEntity(bookingResponseDto, HttpStatus.CREATED);
    }
}
