package com.fbs.central_api.dto;

import lombok.*;

/**
 * Notification payload used to tell an airline admin why registration was rejected.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AirlineRejectDto {
    String airlineAdminName;
    String airlineAdminEmail;
    String rejectReason;
}
