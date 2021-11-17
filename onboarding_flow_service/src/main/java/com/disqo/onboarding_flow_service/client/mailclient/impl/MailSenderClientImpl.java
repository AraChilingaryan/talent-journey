package com.disqo.onboarding_flow_service.client.mailclient.impl;

import com.disqo.onboarding_flow_service.client.mailclient.MailSenderClient;
import com.disqo.onboarding_flow_service.client.mailclient.dto.MailDTO;
import com.disqo.onboarding_flow_service.config.MailProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MailSenderClientImpl implements MailSenderClient {

    private final RestTemplate restTemplate;
    private final MailProperties mailProperties;

    public MailSenderClientImpl(RestTemplate restTemplate, MailProperties mailProperties) {
        this.restTemplate = restTemplate;
        this.mailProperties = mailProperties;
    }

    public void sendEmail(MailDTO mailDTO) {
        HttpEntity<MailDTO> httpEntity = new HttpEntity<>(mailDTO);
        restTemplate.exchange(mailProperties.getBaseUrl() + mailProperties.getPath(),
                HttpMethod.POST, httpEntity, Void.class);
    }
}