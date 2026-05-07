package com.fbs.central_api.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Request payload describing one leg of a connecting flight.
 */
@Data
public class SubFlightDto {
    int priority;
    String sourceAirport;
    String destinationAirport;
    LocalDateTime boardingTime;
    LocalDateTime departureTime;
    LocalDateTime arrivalTime; // Where this subflight will land;
    int boardingMinutes;
    List<SeatMappingDto> seatMappingDtos;
}
