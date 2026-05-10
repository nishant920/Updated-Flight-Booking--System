package com.fbs.central_api.services;

import com.fbs.central_api.connectors.DBApiConnector;
import com.fbs.central_api.dto.BookingRequestDto;
import com.fbs.central_api.dto.BookingResponseDto;
import com.fbs.central_api.enums.UserType;
import com.fbs.central_api.exceptions.UnAuthorizedException;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.models.Booking;
import com.fbs.central_api.models.Flight;
import com.fbs.central_api.utility.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j


@Service
public class BookingService {
    UserService userService;
    FlightService flightService;
    DBApiConnector dbApiConnector;
    Mapper mapper;

    public BookingService(UserService userService,
                          FlightService flightService,
                          DBApiConnector dbApiConnector,
                          Mapper mapper) {
        this.userService = userService;
        this.flightService = flightService;
        this.dbApiConnector = dbApiConnector;
        this.mapper = mapper;
    }

    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto, String authorization){
        String token = authorization.substring(7);

        AppUser customer = userService.getUserFromToken(token);
        if(!UserType.CUSTOMER.equals(customer.getUserType())){
            throw new UnAuthorizedException("Only customers are allowed to book flights");
        }

        Flight flight = flightService.getFlightById(bookingRequestDto.getFlightId());
        Booking booking = mapper.mapBookingRequestDtoToBooking(bookingRequestDto, flight, customer);
        Booking savedBooking = dbApiConnector.callCreateBookingEndpoint(booking);
        return mapper.mapBookingToBookingResponseDto(savedBooking);
    }

}
