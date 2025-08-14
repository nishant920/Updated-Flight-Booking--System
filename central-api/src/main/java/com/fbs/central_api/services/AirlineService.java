package com.fbs.central_api.services;

import com.fbs.central_api.connectors.DBApiConnector;
import com.fbs.central_api.connectors.GeminiConnector;
import com.fbs.central_api.dto.AirlineRegistrationDto;
import com.fbs.central_api.enums.AirlineStatus;
import com.fbs.central_api.enums.UserStatus;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.models.GeminiApiResponse;
import com.fbs.central_api.utility.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AirlineService {
      Mapper mapper;
      DBApiConnector dbApiConnector;
      UserService userService;
      MailService mailService;
      GeminiConnector geminiConnector;

      @Autowired
      public AirlineService(Mapper mapper, DBApiConnector dbApiConnector, UserService userService, MailService mailService,
                            GeminiConnector geminiConnector){
          this.mapper=mapper;
          this.dbApiConnector=dbApiConnector;
          this.userService=userService;
          this.mailService=mailService;
          this.geminiConnector=geminiConnector;
      }

    public Airline getAirlineById(UUID airlineId){
        // so, to get the airline by id we need to call database api
        // so, to call database api we require database api connector.
        return dbApiConnector.callGetAirlineByIdEndpoint(airlineId);
    }

    public Airline updateAirlineDetails(Airline airline){
          return dbApiConnector.callUpdateAirlineEndpoint(airline);

    }

    public Airline registerAirline(AirlineRegistrationDto airlineRegistrationDto){
        log.info("airlineService registerAirline method called: " + airlineRegistrationDto.toString());

        AppUser airlineAdmin = mapper.mapAirlineDetailsToAppUser(airlineRegistrationDto);

        airlineAdmin = dbApiConnector.callCreateUserEndpoint(airlineAdmin);

        Airline airline = mapper.mapAirlineDetailsToAirlineObject(airlineRegistrationDto, airlineAdmin);

         airline = dbApiConnector.callCreateAirlineEndpoint(airline);

        List<AppUser> systemAdminList = userService.getAllSystemAdmins();

        mailService.mailSystemAdminForAirlineRegistration(systemAdminList, airline);

         return airline;
    }

    public Airline acceptAirline(UUID airlineId){
        // 1. to get the airline object on the basis of Id.
        // 2. Update the status of airline as well status of airline Admin.
        // 3. Save those changes into their respective tables in the database.
        // 4. We need to mail airline admin that congratulations your request got approved.
        log.info("airlineId " + airlineId);
        Airline airline = getAirlineById(airlineId);
        airline.setStatus(AirlineStatus.ACTIVE.toString());
        airline.setUpdatedAt(LocalDateTime.now());
        airline = updateAirlineDetails(airline);
        log.info("airlineId " + airline);

        AppUser airlineAdmin = new AppUser();
        airlineAdmin.setStatus(UserStatus.ACTIVE.toString());
        airlineAdmin.setUpdatedAt(LocalDateTime.now());
        userService.updateUserDetails(airlineAdmin);

        mailService.notifyAcceptRequestToAirlineAdmin(airline);

        return airline;
    }

    public void rejectAirlineRequest(UUID airlineId){
          // Verify the ID of airline is correct or not?
                // If it is correct -> Then fine else we will throw the exception
                Airline airline  = this.getAirlineById(airlineId);
                airline.setStatus(AirlineStatus.REJECTED.toString());
                this.updateAirlineDetails(airline);
        // We need to generate rejection reasons
        String prompt = "Generate Failure Reason for the airline details : " + airline.toString();
        GeminiApiResponse geminiApiResponse = geminiConnector.callGeminiGenAIEndpoint(prompt);
        String res = geminiApiResponse.getCandidates().get(0).getContent().getParts().get(0).getText();
        // We need to mail this result to airline admin that his request got called because of reasons
        // I need to call notification api such that Airline admin will recive mail that these are the reasons your airline got rejected.
        // Mail api ->
        mailService.notifyRejectRequestToAirlineAdmin(airline.getAdmin().getEmail(), airline.getAdmin().getName(), res);

    }
    public Airline getAirlineByAdminId(UUID adminId){
        // dbApiConnector to get the airline
        return dbApiConnector.callGetAirlineByAdminIdEndpoint(adminId);
    }
}
