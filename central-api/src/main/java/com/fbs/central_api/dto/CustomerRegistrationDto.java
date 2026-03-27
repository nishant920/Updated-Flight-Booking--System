package com.fbs.central_api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerRegistrationDto {
    String name;
    String email;
    String passward;
    Long contactNumber;
}
