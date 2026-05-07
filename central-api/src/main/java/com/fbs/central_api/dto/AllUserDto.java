package com.fbs.central_api.dto;

import com.fbs.central_api.models.AppUser;
import lombok.*;

import java.util.List;

/**
 * Internal wrapper for user lists returned from db-api.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AllUserDto {
    List<AppUser> appUsers;
}
