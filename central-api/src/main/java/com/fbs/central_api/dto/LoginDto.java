package com.fbs.central_api.dto;

import lombok.Data;

/**
 * Request payload used for user login.
 */
@Data
public class LoginDto {
    String email;
    String password;
}
