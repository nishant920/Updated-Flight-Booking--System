package com.fbs.db_api.model;

import jakarta.persistence.*;
import lombok.Data;
//this model will only use by non-connecting flights
import java.util.UUID;
/*
This flight seat mapping model will only be used for non connecting flights
 */

@Data
@Entity
@Table(name = "flightseatmapping")
public class FlightSeatMapping extends SeatMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @ManyToOne
    Flight flight;
}
