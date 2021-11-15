package com.disqo.interview_flow_service.client;

import com.disqo.interview_flow_service.service.dto.TalentDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JourneyClient {
    private final RestTemplate restTemplate;
    private final static String journeyUrl = "localhost:8080";

    public JourneyClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendFinalResult(TalentDTO talentDTO) {
       restTemplate.postForEntity(journeyUrl+"", talentDTO,Void.class);
    }
}
