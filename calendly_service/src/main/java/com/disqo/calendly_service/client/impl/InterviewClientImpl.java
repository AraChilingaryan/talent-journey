package com.disqo.calendly_service.client.impl;

import com.disqo.calendly_service.client.InterviewClient;
import com.disqo.calendly_service.client.InterviewEventDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
public class InterviewClientImpl implements InterviewClient {
    public static final String BASE_URL = "http://localhost:8087";
    public static final String PATH = "/addEvent";

    private final RestTemplate restTemplate;

    public InterviewClientImpl(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void postInterviewEventDTO(InterviewEventDTO interviewEventDTO) {
        HttpEntity<InterviewEventDTO> httpEntity = new HttpEntity<>(interviewEventDTO);
        restTemplate.exchange(BASE_URL + PATH, HttpMethod.POST, httpEntity, Void.class);
    }

    public InterviewEventDTO generateEventDTOFrom(Map<String, Object> httpEventBody, Map<String, String> eventDetails) {
        InterviewEventDTO interviewEventDTO = new InterviewEventDTO();
        interviewEventDTO.setEndTime(httpEventBody.get("end_time").toString());
        interviewEventDTO.setStartTime(httpEventBody.get("start_time").toString());
        interviewEventDTO.setName(httpEventBody.get("name").toString());
        interviewEventDTO.setEventType(eventDetails.get("status"));
        interviewEventDTO.setTalentEmail(eventDetails.get("eventParticipant"));

        return interviewEventDTO;
    }
}

//    private Date parseDate(String stringDate) {
//        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
//        try {
//            return inputFormat.parse(stringDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

