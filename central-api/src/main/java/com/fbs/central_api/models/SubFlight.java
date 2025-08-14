package com.fbs.central_api.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubFlight {
    //id	flightid	starting 	ending	layover	boarding time	departure time	arrival time
    UUID id;
    Flight flight;
    int priority;
    String sourceAirport;
    String destinationAirport;
    LocalDateTime boardingTime;
    LocalDateTime departureTime;
    LocalDateTime arrivalTime; // Where this subflight will land;
    int boardingMinutes;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}