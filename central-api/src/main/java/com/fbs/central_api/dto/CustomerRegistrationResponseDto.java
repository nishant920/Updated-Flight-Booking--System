package com.fbs.central_api.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerRegistrationResponseDto {
    UUID id;
    String name;
    String email;
    Long number;
    String userType;
    String status;
}
