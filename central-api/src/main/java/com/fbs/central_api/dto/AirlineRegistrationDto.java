package com.fbs.central_api.dto;


import lombok.*;

/**
 * Request payload used when a new airline applies for registration.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AirlineRegistrationDto {
    String website;
    String airlineName;
    String companyName;
    int employees;
    int totalFlights;
    String email;
    String password;
    Long contactNumber;
}
