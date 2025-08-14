package com.fbs.notification_api.controllers;

import com.fbs.notification_api.dto.AirlineRejectDto;
import com.fbs.notification_api.model.Airline;
import com.fbs.notification_api.services.AirlineNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notify/airline")
public class AirlineNotificationController {

    AirlineNotificationService airlineNotificationService;
    @Autowired
    public AirlineNotificationController(AirlineNotificationService airlineNotificationService){
        this.airlineNotificationService=airlineNotificationService;
    }

    @PutMapping("/admin/accept-request")
    public void airlineAdminAcceptNotification(@RequestBody Airline airline){
        airlineNotificationService.airlineAcceptRequestNotification(airline);
    }

    @PutMapping("/admin/reject-request")
    public void airlineadminRejectNotification(@RequestBody AirlineRejectDto airlineRejectDto){
        airlineNotificationService.notifyAirlineAdminRejectNotification(airlineRejectDto);
    }
}
