package com.fbs.db_api.dto;

import com.fbs.db_api.model.Flight;
import lombok.Data;

import java.util.List;

@Data
public class AllFlightDto {
    List<Flight> flights;
}
