package com.fbs.central_api.connectors;

import com.fbs.central_api.dto.AllUserDto;
import com.fbs.central_api.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class DBApiConnector {

    RestTemplate restTemplate;

    public DBApiConnector(RestTemplate restTemplate){
        this.restTemplate=restTemplate;

    }

    @Value("${db.api.url}")
    String dbApiBaseUrl;

    public AppUser callCreateUserEndpoint(AppUser user){
        log.info("Inside callCreateUserEndpoint method with user object: " + user.toString());

        String url = dbApiBaseUrl + "/user/create";

        RequestEntity<AppUser> request = RequestEntity.post(url).body(user);
        log.info("Created request : " + request.toString());

        log.info("Calling dbApi create user endpoint");
        ResponseEntity<AppUser> response = restTemplate.exchange(url, HttpMethod.POST, request, AppUser.class);
        log.info("Responce: " + response.toString());
       return response.getBody();
    }

    public Airline callCreateAirlineEndpoint(Airline airline){
        log.info("Inside callCreateAirlineEndpoint with payload: " + airline.toString());
        // 1. Create url
        String url = dbApiBaseUrl + "/airline/create";
        // 2. create request
        RequestEntity request = RequestEntity.post(url).body(airline);
        // 3. Create resttemplate object
        // 4. By using restTemplate.exchange method to call this endpoint
        ResponseEntity<Airline> response = restTemplate.exchange(url, HttpMethod.POST, request, Airline.class);
        return response.getBody();
    }

    public List<AppUser> callGetAllUsersByUserType(String userType){
        String url = dbApiBaseUrl + "/user/get/"+ userType;

        RequestEntity request = RequestEntity.get(url).build();

        ResponseEntity<AllUserDto> response = restTemplate.exchange(url, HttpMethod.GET, request, AllUserDto.class);
        return response.getBody().getAppUsers();

    }
    public Airline callGetAirlineByIdEndpoint(UUID airlineId){
        String url = dbApiBaseUrl + "/airline/" + airlineId.toString();

        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<Airline> resp = restTemplate.exchange(url, HttpMethod.GET, request, Airline.class);
        return resp.getBody();
    }

    public Airline callUpdateAirlineEndpoint(Airline airline){
        String url = dbApiBaseUrl + "/airline/update";
        RequestEntity request = RequestEntity.put(url).body(airline);
        ResponseEntity<Airline> response = restTemplate.exchange(url, HttpMethod.PUT, request, Airline.class);
        return response.getBody();
    }

    public AppUser callUpdateUserEndpoint(AppUser user){
        String url = dbApiBaseUrl + "/user/update";
        RequestEntity request = RequestEntity.put(url).body(user);

        ResponseEntity<AppUser> resp = restTemplate.exchange(url, HttpMethod.PUT, request, AppUser.class);
        return resp.getBody();
    }

    public AppUser callGetUserByEmailEndpoint(String email){
        // Are we having any endpoint related to this.
        String url = dbApiBaseUrl + "/user/email/" + email;
        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<AppUser> resp = restTemplate.exchange(url, HttpMethod.GET, request, AppUser.class);
        return resp.getBody();
    }

      public Airline callGetAirlineByAdminIdEndpoint(UUID adminId){
          String url = dbApiBaseUrl + "/airline/get/admin/" +  adminId.toString();
          RequestEntity request = RequestEntity.get(url).build();
          ResponseEntity<Airline> resp = restTemplate.exchange(url, HttpMethod.GET, request, Airline.class);
          return resp.getBody();
      }

      public Aircraft callSaveAircraftEndpoint(Aircraft aircraft){
        String url = dbApiBaseUrl + "/aircraft/save";
        RequestEntity<Aircraft> request = RequestEntity.post(url).body(aircraft);
        ResponseEntity<Aircraft> resp = restTemplate.exchange(url, HttpMethod.POST, request, Aircraft.class);
        return resp.getBody();
      }



    public Aircraft callGetAircraftById(UUID Id){
        String url = dbApiBaseUrl + "/aircraft/save";
        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<Aircraft> response = restTemplate.exchange(url, HttpMethod.GET, request, Aircraft.class);
        return response.getBody();
    }

    public Flight callCreateFlightEndpoint(Flight flight){
        String url = dbApiBaseUrl + "/flight/create";
        RequestEntity request = RequestEntity.post(url).body(flight);
        ResponseEntity<Flight> response = restTemplate.exchange(url, HttpMethod.POST, request, Flight.class);
        return response.getBody();
    }

    public FlightSeatMapping callCreateFlightSeatMapping(FlightSeatMapping flightSeatMapping){
        String url = dbApiBaseUrl + "/create";
        RequestEntity request = RequestEntity.post(url).body(flightSeatMapping);
        ResponseEntity<FlightSeatMapping> response = restTemplate.exchange(url, HttpMethod.POST, request, FlightSeatMapping.class);
        return response.getBody();
    }
    public SubFlight callCreateSubFlightEndpoint(SubFlight subFlight){
        String url = dbApiBaseUrl + "/subflight/create";
        RequestEntity request = RequestEntity.post(url).body(subFlight);
        ResponseEntity<SubFlight> response = restTemplate.exchange(url, HttpMethod.POST, request, SubFlight.class);
        return response.getBody();
    }

    public Object callSearchFlightEndpoint(String sourceAirport,
                                           String destinationAirport,
                                           String dateTime){

        sourceAirport = sourceAirport.replace( ' ', '+');
        destinationAirport = destinationAirport.replace(' ','+');
        dateTime=dateTime.replace(' ','+');
        String url = dbApiBaseUrl + "/flight/search?" + "sourceAirport="+sourceAirport+"&" + "destinationAirport=" + destinationAirport +"&" + "dateTime=" + dateTime;
        log.info(url);
        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<Object> resp = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
        return resp.getBody();
    }
}
