package com.fbs.central_api.dto;

import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import lombok.*;

/**
 * Notification payload sent to system admins for airline registration review.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AirlineRegistrationReqDto {
    AppUser appAdmin;
    Airline airline;
}
