package com.fbs.central_api.dto;

import com.fbs.central_api.models.Flight;
import lombok.*;

import java.util.List;

/**
 * Internal wrapper for raw flight records returned from db-api.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AllFlightDto {
       List<Flight> flights;
}
