package com.fbs.central_api.services;

import com.fbs.central_api.connectors.NotificationApiConnector;
import com.fbs.central_api.dto.AirlineRegistrationReqDto;
import com.fbs.central_api.dto.AirlineRejectDto;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MailService {

    NotificationApiConnector notificationApiConnector;

    @Autowired
    public MailService(NotificationApiConnector notificationApiConnector){
        this.notificationApiConnector = notificationApiConnector;
    }
    /*
This function is responsible for sending mail to all the system admins regarding airline registration
*/
    public void mailSystemAdminForAirlineRegistration(List<AppUser> systemAdmins, Airline airline) {
        // We will apply one loop over all the system admins and one by one we will mail all the system admins

        for (AppUser systemAdmin : systemAdmins) {
            AirlineRegistrationReqDto airlineRegistrationReqDto = new AirlineRegistrationReqDto();
            airlineRegistrationReqDto.setAirline(airline);
            airlineRegistrationReqDto.setAppAdmin(systemAdmin);
            try {
                notificationApiConnector.notifySystemAdminForAirlineRegistration(airlineRegistrationReqDto);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
    public void notifyAcceptRequestToAirlineAdmin(Airline airline){
        try{
            notificationApiConnector.notifyAcceptRequestToAirlineAdmin(airline);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }
    public void notifyRejectRequestToAirlineAdmin(String email, String name, String rejectReason){
        AirlineRejectDto airlineRejectDto = new AirlineRejectDto();
        airlineRejectDto.setAirlineAdminEmail(email);
        airlineRejectDto.setAirlineAdminName(name);
        airlineRejectDto.setRejectReason(rejectReason);
        try{
            notificationApiConnector.notifyRejectRequestToAirlineAdmin(airlineRejectDto);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
