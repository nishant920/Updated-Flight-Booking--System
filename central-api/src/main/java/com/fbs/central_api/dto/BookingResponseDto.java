package com.fbs.central_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingResponseDto {
    UUID bookingId;
    UUID flightId;
    String passengerName;
    String customerName;
    String airlineName;
    String sourceAirport;
    String destinationAirport;
    String flightType;
    LocalDateTime departureTime;
    LocalDateTime arrivalTime;
    LocalDateTime bookedAt;
    int totalAmount;
}
