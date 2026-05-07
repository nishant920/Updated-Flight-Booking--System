package com.fbs.central_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Public flight result returned to customers during flight search.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FlightSearchResponseDto {
    UUID flightId;
    String airlineName;
    String aircraftModelName;
    String sourceAirport;
    String destinationAirport;
    String flightType;
    int totalTime;
    LocalDateTime boardingTime;
    int boardingMinutes;
    LocalDateTime departureTime;
    LocalDateTime arrivalTime;
    boolean connecting;
}
