package com.fbs.db_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
public class SubFlightSeatMapping extends SeatMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @ManyToOne
    SubFlight flight;
}
