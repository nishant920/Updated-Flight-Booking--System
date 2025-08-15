package com.fbs.db_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Entity
@Table(name = "flightbookedseats")
public class FlightSeatBooked extends SeatBooked {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @ManyToOne
    Flight flight;
    @ManyToOne
    AppUser bookedBy;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
