package com.fbs.db_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;
@Data
@Entity
@Table(name="flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @ManyToOne
    Airline airline;
    @ManyToOne
    Aircraft aircraft;
    String sourceAirport;
    String destinationAirport;
    String flightType;
    int totalTime;
   LocalDate boardingTime; //time when passengers needs to be in airport
    int boardingMinutes;
   LocalDate departureTime;
   LocalDate arrivalTime;

   Boolean isConnecting; //
}
