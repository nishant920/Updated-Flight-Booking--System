package com.fbs.db_api.controllers;

import com.fbs.db_api.dto.AllUserDto;
import com.fbs.db_api.model.AppUser;
import com.fbs.db_api.repositories.AppUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/api/v1/db/user")
public class AppUserController {

    AppUserRepo appUserRepo;

    @Autowired
    public AppUserController(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @PostMapping("/create")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) {
        // log.info("");
        AppUser appUser1 = appUserRepo.save(appUser);
        return new ResponseEntity<>(appUser1, HttpStatus.CREATED);
    }

    @GetMapping("/get/{userType}")
    public ResponseEntity<AppUser> getAllUserByUserType(@PathVariable String userType) {
        List<AppUser> users = appUserRepo.findAllByUserType(userType);
        AllUserDto allUserDto = new AllUserDto();
        allUserDto.setAppUsers(users);
        return new ResponseEntity(allUserDto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<AppUser> update(@RequestBody AppUser user){
        appUserRepo.save(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }
}