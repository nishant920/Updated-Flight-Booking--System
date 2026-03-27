package com.fbs.notification_api.controllers;

import com.fbs.notification_api.dto.AirlineRejectDto;
import com.fbs.notification_api.dto.CustomerRegistrationNotificationDto;
import com.fbs.notification_api.model.Airline;
import com.fbs.notification_api.services.AirlineNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Slf4j
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

    @PutMapping("/customer/register")
    public void customerRegistrationNotification(@RequestBody CustomerRegistrationNotificationDto customerRegistrationNotificationDto){
        log.info("Received customer registration notification request for email: {}", customerRegistrationNotificationDto.getEmail());
        airlineNotificationService.notifyCustomerRegistration(customerRegistrationNotificationDto);
    }
}
