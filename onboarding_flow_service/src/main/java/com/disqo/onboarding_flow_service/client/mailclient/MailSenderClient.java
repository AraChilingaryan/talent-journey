package com.disqo.onboarding_flow_service.client.mailclient;

import com.disqo.onboarding_flow_service.client.mailclient.dto.MailDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MailSenderClient {

    public String BASE_URL;
    public String PATH_ITEM;

    private final RestTemplate restTemplate;

    public MailSenderClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendEmail(MailDTO mailDTO) {
        HttpEntity<MailDTO> httpEntity = new HttpEntity<>(mailDTO);
        restTemplate.exchange(BASE_URL + PATH_ITEM, HttpMethod.POST, httpEntity, Void.class);
    }
}