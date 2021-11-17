package com.disqo.calendly_service.service.impl;

import com.disqo.calendly_service.client.InterviewClient;
import com.disqo.calendly_service.client.InterviewEventDTO;
import com.disqo.calendly_service.config.CalendlyProperties;
import com.disqo.calendly_service.service.CalendarService;
import com.disqo.calendly_service.service.dto.EventResponse;
import com.disqo.calendly_service.service.dto.WebhookDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CalendarServiceImpl implements CalendarService {

    private static final Logger log = LoggerFactory.getLogger(CalendarServiceImpl.class);

    private final InterviewClient interviewClient;
    private final CalendlyProperties calendlyProperties;

    public CalendarServiceImpl(final InterviewClient interviewClient,
                               final CalendlyProperties calendlyProperties) {
        this.interviewClient = interviewClient;
        this.calendlyProperties = calendlyProperties;
    }

    @Override
    public void sendEventToClient(final WebhookDto webhook) {
        log.info("Start sendEventToClient");
        final String eventUri = webhook.getPayload().getEventUri();
        final EventResponse event = getEvent(eventUri);
        final InterviewEventDTO interviewEvent = interviewClient.generateInterviewEvent(webhook, event.getResource());
        interviewClient.postInterviewEventDTO(interviewEvent);
        log.info("Finished sendEventToClient");
    }

    public EventResponse getEvent(final String uri) {
        log.info("Start getEvent");
        final RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + calendlyProperties.getMyAccessToken());
        HttpEntity<EventResponse> httpEntity = new HttpEntity<>(headers);
        log.info("Finished getEvent");
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, EventResponse.class).getBody();
    }

    public <T> void post(String uri, T data) {
        log.info("Start post");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<T> httpEntity = new HttpEntity<>(data);
        restTemplate.exchange(uri, HttpMethod.POST, httpEntity, Void.class);
        log.info("Finished post");
    }
}