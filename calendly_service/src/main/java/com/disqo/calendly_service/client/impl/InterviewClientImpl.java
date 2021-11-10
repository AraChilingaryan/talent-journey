package com.disqo.calendly_service.client.impl;

import com.disqo.calendly_service.client.InterviewClient;
import com.disqo.calendly_service.client.InterviewEventDTO;
import com.disqo.calendly_service.config.InterviewProperties;
import com.disqo.calendly_service.service.dto.EventDto;
import com.disqo.calendly_service.service.dto.WebhookDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InterviewClientImpl implements InterviewClient {

    private final RestTemplate restTemplate;
    private final InterviewProperties properties;

    public InterviewClientImpl(final RestTemplate restTemplate,
                               final InterviewProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    public void postInterviewEventDTO(final InterviewEventDTO interviewEventDTO) {
        final HttpEntity<InterviewEventDTO> httpEntity = new HttpEntity<>(interviewEventDTO);
        final String finalUrl = properties.getUrl() + properties.getPath();
        restTemplate.exchange(finalUrl, HttpMethod.POST, httpEntity, Void.class);
    }

    public InterviewEventDTO generateInterviewEvent(final WebhookDto webhook, final EventDto event) {
        return InterviewEventDTO.builder()
                .talentEmail(webhook.getPayload().getParticipantEmail())
                .eventType(webhook.getEvent())
                .participantName(webhook.getPayload().getParticipantName())
                .endTime(event.getEndTime())
                .startTime(event.getStartTime())
                .build();
    }
}

