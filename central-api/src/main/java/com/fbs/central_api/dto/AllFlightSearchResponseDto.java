package com.fbs.central_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Public response wrapper for customer-safe flight search results.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AllFlightSearchResponseDto {
    List<FlightSearchResponseDto> flights;
}
