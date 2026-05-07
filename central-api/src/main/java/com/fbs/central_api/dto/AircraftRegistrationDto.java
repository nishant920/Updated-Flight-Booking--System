package com.fbs.central_api.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * Request payload used by an airline admin to register an aircraft.
 */
@Data
public class AircraftRegistrationDto {
    int modelNumber;
    String manufacturer;
    String modelName;
    int totalFlights;
    LocalDate buildDate;
    int capacity;
}
