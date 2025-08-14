package com.fbs.db_api.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table
public class SubFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID Id;
    @ManyToOne
    Flight flight;
    int priority;
    String sourceAirport;
    String destinationAirport;
    LocalDateTime boardingTime;
     int waitTime;
    LocalDateTime departingTime;
    LocalDateTime arrivalTime;
}
