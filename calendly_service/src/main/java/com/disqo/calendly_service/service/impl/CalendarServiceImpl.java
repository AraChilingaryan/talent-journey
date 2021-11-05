package com.disqo.calendly_service.service.impl;

import com.disqo.calendly_service.client.InterviewClient;
import com.disqo.calendly_service.client.InterviewEventDTO;
import com.disqo.calendly_service.service.CalendarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class CalendarServiceImpl implements CalendarService {

    private final InterviewClient interviewClient;

    @Value("${my.access.token}")
    private String myAccessToken;

    public CalendarServiceImpl(final InterviewClient interviewClient) {
        this.interviewClient = interviewClient;
    }

    @Override
    public void sendEventToClient(final Map<String, Object> webhookResponse) throws IOException, InterruptedException {
        Map<String, String> eventDetails = getEventDetails(webhookResponse);
        String response = getRequestToCalendar(eventDetails.get("URI"));
        Map<String, Object> event = parseJsonToMap(response);
        InterviewEventDTO interviewEventDTO = interviewClient.generateEventDTOFrom(event, eventDetails);

        interviewClient.postInterviewEventDTO(interviewEventDTO);
    }

    public String getRequestToCalendar (final String uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + myAccessToken)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    private Map<String, String> getEventDetails(final Map<String, Object> webhook) {
        final Map<String, String> eventDetails = new HashMap<>();
        String eventStatus = webhook.get("event").toString();
        eventDetails.put("status", eventStatus);
        Object payload = webhook.get("payload");
        Map<String, Object> event = (Map<String, Object>)payload;
        eventDetails.put("URI", event.get("event").toString());
        eventDetails.put("eventParticipant", event.get("email").toString());
        return eventDetails;
    }

    private Map<String, Object> parseJsonToMap(final String s) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, Object>> typeRef = new TypeReference<>() {
        };
        try {
            return objectMapper.readValue(s, typeRef);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Ask for useless of this method(send using calendar service or using client service)
    public <T> void post(String uri, T data) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<T> httpEntity = new HttpEntity<>(data);
        restTemplate.exchange(uri, HttpMethod.POST, httpEntity, Void.class);
    }
}

//    public <T> int post(String uri, String data) throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newBuilder().build();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(uri))
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(data))
//                .build();
//        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.discarding());
//        return response.statusCode();
//    }