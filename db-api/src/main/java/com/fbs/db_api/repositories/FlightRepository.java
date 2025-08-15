package com.fbs.db_api.repositories;

import com.fbs.db_api.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {
    @Query(value = "select * from flights where source_airport=:sourceAirport and destination_airport=:destinationAirport and departure_time >= CAST (:dateTime AS TIMESTAMP)", nativeQuery = true)
    public List<Flight> getAllFlights(String sourceAirport,
                                      String destinationAirport,
                                      String dateTime);

}
