package com.fbs.db_api.repositories;

import com.fbs.db_api.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AppUserRepo extends JpaRepository<AppUser, UUID> {

    public List<AppUser> findAllByUserType(String userType);
}
