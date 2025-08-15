package com.fbs.db_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Entity
public class SubFlightSeatMapping extends SeatMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @ManyToOne
    SubFlight flight;
    String className;
    String range; // 1-20
    int basePrice;
    int windowPrice;
    int totalWindow;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
