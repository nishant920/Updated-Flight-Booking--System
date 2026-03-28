package com.fbs.db_api.controllers;

import com.fbs.db_api.dto.AllFlightDto;
import com.fbs.db_api.model.Flight;
import com.fbs.db_api.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/flight")
public class FlightController {

    FlightRepository flightRepository;
    @Autowired
    public FlightController(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    @PostMapping("/create")
    public Flight createFlight(@RequestBody Flight flight){
        flightRepository.save(flight);
        return flight;
    }

    @GetMapping("/search")
    public AllFlightDto searchFlight(@RequestParam String sourceAirport,
                                     @RequestParam String destinationAirport,
                                     @RequestParam String dateTime){
        List<Flight> flights = flightRepository.getAllFlights(sourceAirport, destinationAirport, dateTime.toString());
        AllFlightDto allFlightDto = new AllFlightDto();
        allFlightDto.setFlights(flights);
        return allFlightDto;
    }
    @GetMapping("/{flightId}")
    public Flight getFlightById(@PathVariable UUID flightId){
        return flightRepository.findById(flightId).orElse(null);
    }

}