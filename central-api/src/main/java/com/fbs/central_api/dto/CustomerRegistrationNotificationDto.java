package com.fbs.central_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Notification payload used to send customer registration confirmation.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerRegistrationNotificationDto {
    String email;
    String name;
}
