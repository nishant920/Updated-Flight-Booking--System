package com.fbs.db_api.dto;

import com.fbs.db_api.model.AppUser;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AllUserDto {
    List<AppUser> appUsers;
}
