package com.fbs.db_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;


@Data
@Entity
@Table(name = "aircrafts")
public class Aircraft {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    UUID Id;
    int modelNumber;
    String manufacturer;
    String modelName;
    int totalFlight;
    LocalDate buildDate;
    @ManyToOne
    Airline airline;
    int capacity;

}
