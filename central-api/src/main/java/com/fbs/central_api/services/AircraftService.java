package com.fbs.central_api.services;

import com.fbs.central_api.connectors.DBApiConnector;
import com.fbs.central_api.dto.AircraftRegistrationDto;
import com.fbs.central_api.enums.UserType;
import com.fbs.central_api.exceptions.UnAuthorizedException;
import com.fbs.central_api.models.Aircraft;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.utility.Mapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AircraftService {

    UserService userService;
    AirlineService airlineService;
    Mapper mapper;
    DBApiConnector dbApiConnector;
    @Autowired
    public AircraftService(UserService userService, AirlineService airlineService,
                           Mapper mapper, DBApiConnector dbApiConnector ){
        this.userService=userService;
        this.airlineService=airlineService;
        this.mapper=mapper;
        this.dbApiConnector=dbApiConnector;

    }

    public Aircraft getAirCraftrById(UUID id){
        return dbApiConnector.callGetAircraftById(id);
    }

    public Aircraft registerAircraft(AircraftRegistrationDto aircraftRegistrationDto, String authorization){
        String token = authorization.substring(7);
        AppUser airlineAdmin = userService.getUserFromToken(token);
        if(!airlineAdmin.getUserType().equals(UserType.AIRLINE_ADMIN.toString())){
            throw new UnAuthorizedException("User is not allowed to register aircraft");
        }
        // With the help of airlineAdmin we need to get airline object
        Airline airline = airlineService.getAirlineByAdminId(airlineAdmin.getId());
        // Mapping logic to map aircraftdto to aircraft
        Aircraft aircraft = mapper.mapAircraftDtoToAircraft(aircraftRegistrationDto, airline);
        // Now we need to call save aircraft method.
        return saveAircraft(aircraft);
    }

    public Aircraft saveAircraft(Aircraft aircraft){
        // We need to call dbApiConnector
        return dbApiConnector.callSaveAircraftEndpoint(aircraft);
    }
}
