package com.fbs.central_api.services;

import com.fbs.central_api.connectors.DBApiConnector;
import com.fbs.central_api.dto.FlightDetailsDto;
import com.fbs.central_api.dto.SeatMappingDto;
import com.fbs.central_api.dto.SubFlightDto;
import com.fbs.central_api.enums.UserType;
import com.fbs.central_api.exceptions.UnAuthorizedException;
import com.fbs.central_api.models.*;
import com.fbs.central_api.utility.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FlightService{

    Mapper mapper;
    DBApiConnector dbApiConnector;
    UserService userService;
    AirlineService airlineService;
    AircraftService aircraftService;
    @Autowired
    public FlightService(Mapper mapper, DBApiConnector dbApiConnector, UserService userService,
                         AirlineService airlineService, AircraftService aircraftService ){
        this.mapper=mapper;
        this.dbApiConnector=dbApiConnector;
        this.userService=userService;
        this.airlineService=airlineService;
        this.aircraftService=aircraftService;
    }
    public void createSubFlight(List<SubFlightDto> subFlightDtos, Flight flight){
        for(SubFlightDto subFlightDto : subFlightDtos){
            // We need to map subFlightDto one by one to SubFlightModel
            SubFlight subFlight = mapper.mapSubFlightDtoToSubFlightModel(subFlightDto, flight);
            // db Api connector to save subFlight into the database.
            dbApiConnector.callCreateSubFlightEndpoint(subFlight);
        }
    }
    public Flight createFlight(FlightDetailsDto flightDetailsDto,
                               String authorization){
        String token = authorization.substring(7);
        AppUser user = userService.getUserFromToken(token);
        if(!user.getUserType().equals(UserType.AIRLINE_ADMIN.toString())){
            throw new UnAuthorizedException("User is not allowed to create flight");
        }
        Airline airline = airlineService.getAirlineByAdminId(user.getId());
        Aircraft aircraft = aircraftService.getAirCraftrById(flightDetailsDto.getAirCraftID());

        Flight flight = mapper.mapFlightDetailsDtoToFightModel(flightDetailsDto, airline, aircraft);
        // We need to save the flight inside the database.
        flight = dbApiConnector.callCreateFlightEndpoint(flight);

        if(flightDetailsDto.getSubFlightDtos().size() > 0){
            createSubFlight(flightDetailsDto.getSubFlightDtos(), flight);
        }
        List<SeatMappingDto> seatMappingDtos = flightDetailsDto.getSeatMappingDtos();
        for(int i = 0; i < seatMappingDtos.size(); i++){
            SeatMappingDto seatMappingDto = seatMappingDtos.get(i);
            // Mapper
            FlightSeatMapping flightSeatMapping = mapper.mapFlightSeatMappingDtoToModel(seatMappingDto, flight);
            // dbApiConnector to save the flightSeatMapping
            dbApiConnector.callCreateFlightSeatMapping(flightSeatMapping);
        }
        // Mail -> We need to mail to the flight Admin that your requested flight got created inside the system.
        return flight;
    }
}
