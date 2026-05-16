package com.fbs.central_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response returned after successful login.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    String token;
    String name;
    String email;
    String message;
}
