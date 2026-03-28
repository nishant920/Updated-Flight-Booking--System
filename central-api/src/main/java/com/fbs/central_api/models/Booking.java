package com.fbs.central_api.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Booking {

    UUID Id;
    Flight flight;
    List<SubFlight> subFlight;
    AppUser bookedBy;
    int totalAmount;
    String passengerName;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
