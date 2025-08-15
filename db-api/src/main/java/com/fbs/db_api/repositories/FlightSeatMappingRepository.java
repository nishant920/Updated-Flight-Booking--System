package com.fbs.db_api.repositories;

import com.fbs.db_api.model.FlightSeatMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FlightSeatMappingRepository extends JpaRepository<FlightSeatMapping, UUID > {
}
