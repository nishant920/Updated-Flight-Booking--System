package com.fbs.central_api.connectors;

import com.fbs.central_api.models.GeminiApiRequest;
import com.fbs.central_api.models.GeminiApiResponse;
import com.fbs.central_api.models.RequestContent;
import com.fbs.central_api.models.RequestPart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class GeminiConnector {

    RestTemplate restTemplate;
    @Autowired
    public GeminiConnector(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Value("${gemini.genai.url}")
    String geminiApiUrl;

    @Value("${gemini.token}")
    String token;

    String geminiTokenHeader = "X-goog-api-key";
    public GeminiApiResponse callGeminiGenAIEndpoint(String prompt){

        // Creation of the request body
        GeminiApiRequest geminiApiReqBody = new GeminiApiRequest();
        RequestPart requestPart = new RequestPart();
        requestPart.setText(prompt);
        List<RequestPart> requestParts = new ArrayList<>();
        requestParts.add(requestPart);
        RequestContent requestContent = new RequestContent();
        requestContent.setParts(requestParts);
        List<RequestContent> requestContents = new ArrayList<>();
        requestContents.add(requestContent);
        geminiApiReqBody.setContents(requestContents);
        HttpHeaders headers = new HttpHeaders();
        headers.add(geminiTokenHeader, token);
        headers.add("Content-Type", "application/json");
        HttpEntity<GeminiApiRequest> httpEntity = new HttpEntity<>(geminiApiReqBody, headers);
        ResponseEntity<GeminiApiResponse> response = restTemplate.exchange(geminiApiUrl, HttpMethod.POST, httpEntity, GeminiApiResponse.class);
        return response.getBody();

    }
}
