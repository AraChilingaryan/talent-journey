package com.disqo.interview_flow_service.client;

import com.disqo.interview_flow_service.client.dto.MailDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MailClient {

    public static final String BASE_URL = "http://7252-78-109-72-144.ngrok.io";
    public static final String PATH_ITEM = "/send";

    private final RestTemplate restTemplate;

    public MailClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendEmail(MailDTO mailDTO) {
        HttpEntity<MailDTO> httpEntity = new HttpEntity<>(mailDTO);
        restTemplate.exchange(BASE_URL + PATH_ITEM, HttpMethod.POST, httpEntity, Void.class);
    }
}