package com.fbs.db_api.repositories;

import com.fbs.db_api.model.SubFlight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubFlightRepository extends JpaRepository<SubFlight, UUID> {
}
