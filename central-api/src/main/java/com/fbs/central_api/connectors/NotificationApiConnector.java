package com.fbs.central_api.connectors;

import com.fbs.central_api.dto.AirlineRegistrationReqDto;
import com.fbs.central_api.dto.AirlineRejectDto;
import com.fbs.central_api.models.Airline;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationApiConnector {
 RestTemplate restTemplate;

public NotificationApiConnector(RestTemplate restTemplate){
    this.restTemplate=restTemplate;
}

 @Value("${notification.api.url}")
 String notificationBaseUrl;

    public void notifySystemAdminForAirlineRegistration(AirlineRegistrationReqDto airlineRegistrationReqDto){
        String url = notificationBaseUrl + "/appadmin/airline-registration";
        RequestEntity request = RequestEntity.put(url).body(airlineRegistrationReqDto);
        ResponseEntity<Object> resp = restTemplate.exchange(url, HttpMethod.PUT, request, Object.class);

    }

    public void notifyAcceptRequestToAirlineAdmin(Airline airline){
        String url = notificationBaseUrl + "/admin/accept-request";
        RequestEntity<Airline> request = RequestEntity.put(url).body(airline);
        ResponseEntity<Airline> resp = restTemplate.exchange(url, HttpMethod.PUT, request, Airline.class);

    }

    public void notifyRejectRequestToAirlineAdmin(AirlineRejectDto airlineRejectDto){
        String url = notificationBaseUrl + "/airline/admin/reject-request";
        RequestEntity request = RequestEntity.put(url).body(airlineRejectDto);
        ResponseEntity<Object> resp = restTemplate.exchange(url, HttpMethod.PUT, request, Object.class);
    }

}
