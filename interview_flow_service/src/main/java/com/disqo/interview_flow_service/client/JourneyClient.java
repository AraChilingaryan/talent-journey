package com.disqo.interview_flow_service.client;

import com.disqo.interview_flow_service.service.dto.TalentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JourneyClient {
    private final RestTemplate restTemplate;
    @Value("${interview.flow.journey.client.baseUrl}")
    public String BASE_URL;
    @Value("${interview.flow.journey.client.path}")
    public String PATH_ITEM;


    public JourneyClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendFinalResult(TalentDTO talentDTO) {
       restTemplate.postForEntity(BASE_URL + PATH_ITEM, talentDTO,Void.class);
    }
}
