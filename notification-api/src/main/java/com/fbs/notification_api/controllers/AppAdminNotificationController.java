package com.fbs.notification_api.controllers;


/*
This particular controller is created to send notification to App Admin
 */

import com.fbs.notification_api.dto.AirlineRegistrationReqDto;
import com.fbs.notification_api.services.AppAdminNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/notify/appadmin")
public class AppAdminNotificationController {
    AppAdminNotificationService appAdminNotificationService;

    @Autowired
    public AppAdminNotificationController(AppAdminNotificationService appAdminNotificationService){
        this.appAdminNotificationService=appAdminNotificationService;
    }

    @PutMapping("/airline-registration")
    public ResponseEntity airlineRegistrationRequestNotification(@RequestBody AirlineRegistrationReqDto airlineRegistrationReqDto){
        log.info("Inside airlineRegistrationRequestNotification with payload: " + airlineRegistrationReqDto.toString());
        // from here we need to call AppAdminNotificationService
        // We are calling the service layer which will be sending mail to the application admin
        appAdminNotificationService.sendAirlineRegistrationRequestNotification(airlineRegistrationReqDto);
        return new ResponseEntity(new Object(), HttpStatus.OK);
    }

}
