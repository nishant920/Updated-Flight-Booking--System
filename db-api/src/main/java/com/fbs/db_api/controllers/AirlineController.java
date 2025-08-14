package com.fbs.db_api.controllers;

import com.fbs.db_api.model.Airline;
import com.fbs.db_api.repositories.AirlineRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/airline")
@Slf4j
public class AirlineController {
    AirlineRepo airlineRepo;

    @Autowired
    public AirlineController(AirlineRepo airlineRepo){
        this.airlineRepo=airlineRepo;
    }
    @PostMapping("/create")
    public ResponseEntity<Airline> createUser(@RequestBody Airline airline){

        log.info("RequsetBody:" + airline.toString());
        Airline airline1 = airlineRepo.save(airline);
        return new ResponseEntity<Airline>(airline1, HttpStatus.CREATED);
    }

    @GetMapping("{airlineId}")
    public ResponseEntity<Airline> getAirlineById(@PathVariable UUID airlineId){
        Airline airline = airlineRepo.findById(airlineId).orElse(null);
        return new ResponseEntity<Airline>(airline, HttpStatus.OK);
    }

   @PutMapping("/update")
   public ResponseEntity update(@RequestBody Airline airline) {
       airlineRepo.save(airline);
       return new ResponseEntity(airline, HttpStatus.OK);
   }

}