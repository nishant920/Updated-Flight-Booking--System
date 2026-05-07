package com.fbs.central_api.dto;

import lombok.*;

import java.util.UUID;

/**
 * Request payload used by a customer to create a flight booking.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingRequestDto {
    UUID flightId;
    String passengerName;
}
