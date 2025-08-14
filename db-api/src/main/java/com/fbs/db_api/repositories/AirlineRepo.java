package com.fbs.db_api.repositories;

import com.fbs.db_api.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AirlineRepo extends JpaRepository<Airline, UUID> {
}
