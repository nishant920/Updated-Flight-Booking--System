package com.fbs.central_api.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingRequestDto {
    UUID flightId;
    String passengerName;
}
