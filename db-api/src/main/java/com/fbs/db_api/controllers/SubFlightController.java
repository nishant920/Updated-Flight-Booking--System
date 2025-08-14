package com.fbs.db_api.controllers;

import com.fbs.db_api.model.SubFlight;
import com.fbs.db_api.repositories.SubFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/db/subflight")
public class SubFlightController {

    @Autowired
    SubFlightRepository subFlightRepository;


    @PostMapping("/create")
    public SubFlight createSubFlight(@RequestBody SubFlight subFlight){
        subFlightRepository.save(subFlight);
        return subFlight;
    }
}