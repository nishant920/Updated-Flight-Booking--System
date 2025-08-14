package com.fbs.db_api.repositories;

import com.fbs.db_api.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AircraftRepository extends JpaRepository<Aircraft, UUID> {
}
