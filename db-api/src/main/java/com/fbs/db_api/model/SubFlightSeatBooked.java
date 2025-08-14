package com.fbs.db_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "subflightbookedseat")
public class SubFlightSeatBooked {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @ManyToOne
    SubFlight flight;
    @ManyToOne
    AppUser bookedBy;
}
