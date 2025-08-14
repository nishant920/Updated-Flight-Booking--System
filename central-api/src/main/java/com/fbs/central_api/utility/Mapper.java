package com.fbs.central_api.utility;

import com.fbs.central_api.dto.*;
import com.fbs.central_api.enums.AirlineStatus;
import com.fbs.central_api.enums.UserStatus;
import com.fbs.central_api.enums.UserType;
import com.fbs.central_api.models.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Mapper {
    public AppUser mapAirlineDetailsToAppUser(AirlineRegistrationDto airlineDetails){

        AppUser airlineAdmin = new AppUser();
        airlineAdmin.setEmail(airlineDetails.getEmail());
        airlineAdmin.setName(airlineDetails.getAirlineName() + " Admin");
        airlineAdmin.setUserType(UserType.AIRLINE_ADMIN.toString());
        airlineAdmin.setNumber(airlineDetails.getContactNumber());
        airlineAdmin.setVerified(false);
        airlineAdmin.setStatus(UserStatus.INACTIVE.toString());
        airlineAdmin.setPassword(airlineDetails.getPassword());
        airlineAdmin.setCreatedAt(LocalDateTime.now());
        airlineAdmin.setUpdatedAt(LocalDateTime.now());
        return airlineAdmin;
    }

    public Airline mapAirlineDetailsToAirlineObject(AirlineRegistrationDto airlineRegistrationDto, AppUser airlineAdmin){
                Airline airline = new Airline();
                airline.setAirlineName(airlineRegistrationDto.getAirlineName());
                airline.setStatus(AirlineStatus.INACTIVE.toString());
                airline.setAdmin(airlineAdmin);
                airline.setEmployees(airlineRegistrationDto.getEmployees());
                airline.setTotalFlights(airlineRegistrationDto.getTotalFlights());
                airline.setCompanyName(airlineRegistrationDto.getCompanyName());
                airline.setWebsite(airlineRegistrationDto.getWebsite());
                airline.setCreatedAt(LocalDateTime.now());
                airline.setUpdatedAt(LocalDateTime.now());
                return airline;

    }

    public Aircraft mapAircraftDtoToAircraft(AircraftRegistrationDto aircraftRegistrationDto,
                                             Airline airline){
        Aircraft aircraft = new Aircraft();
        aircraft.setAirline(airline);
        aircraft.setCapacity(aircraftRegistrationDto.getCapacity());
        aircraft.setManufacturer(aircraftRegistrationDto.getManufacturer());
        aircraft.setModelName(aircraftRegistrationDto.getModelName());
        aircraft.setTotalFlights(aircraftRegistrationDto.getTotalFlights());
        aircraft.setBuildDate(aircraftRegistrationDto.getBuildDate());
        aircraft.setModelNumber(aircraftRegistrationDto.getModelNumber());
        return aircraft;
    }

    public Flight mapFlightDetailsDtoToFightModel(FlightDetailsDto flightDetailsDto,
                                                  Airline airline,
                                                  Aircraft aircraft){
        Flight flight = new Flight();
        flight.setAirline(airline);
        flight.setAircraft(aircraft);
        flight.setConnecting(flightDetailsDto.isConnecting());
        flight.setBoardingTime(flightDetailsDto.getBoardingTime());
        flight.setArrivalTime(flightDetailsDto.getArrivalTime());
        flight.setFlightType(flightDetailsDto.getFlightType());
        flight.setBoardingMinutes(flightDetailsDto.getBoardingMinutes());
        flight.setDepartureTime(flightDetailsDto.getDepartureTime());
        flight.setTotalTime(flightDetailsDto.getTotalTime());
        flight.setSourceAirport(flightDetailsDto.getSourceAirport());
        flight.setCreatedAt(LocalDateTime.now());
        flight.setUpdatedAt(LocalDateTime.now());
        flight.setDestinationAirport(flightDetailsDto.getDestinationAirport());

        return flight;
    }

    public SubFlight mapSubFlightDtoToSubFlightModel(SubFlightDto subFlightDto, Flight flight){
        SubFlight subFlight = new SubFlight();
        subFlight.setFlight(flight);
        subFlight.setArrivalTime(subFlightDto.getArrivalTime());
        subFlight.setBoardingMinutes(subFlightDto.getBoardingMinutes());
        subFlight.setDepartureTime(subFlightDto.getDepartureTime());
        subFlight.setPriority(subFlightDto.getPriority());
        subFlight.setDestinationAirport(subFlightDto.getDestinationAirport());
        subFlight.setSourceAirport(subFlightDto.getSourceAirport());
        subFlight.setCreatedAt(LocalDateTime.now());
        subFlight.setUpdatedAt(LocalDateTime.now());
        return subFlight;
    }

    public FlightSeatMapping mapFlightSeatMappingDtoToModel(SeatMappingDto seatMappingDto, Flight flight){
        FlightSeatMapping flightSeatMapping = new FlightSeatMapping();
        flightSeatMapping.setFlight(flight);
        flightSeatMapping.setRange(seatMappingDto.getRange());
        flightSeatMapping.setClassName(seatMappingDto.getClassName());
        flightSeatMapping.setBasePrice(seatMappingDto.getBasePrice());
        flightSeatMapping.setWindowPrice(flightSeatMapping.getWindowPrice());
        flightSeatMapping.setTotalWindow(flightSeatMapping.getTotalWindow());
        flightSeatMapping.setCreatedAt(LocalDateTime.now());
        flightSeatMapping.setUpdatedAt(LocalDateTime.now());
        return flightSeatMapping;
    }
}
