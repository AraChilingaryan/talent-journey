package com.disqo.talent_service.client;

import com.disqo.talent_service.client.dto.MailDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MailClient {

    public static final String BASE_URL = "http://ec2-18-197-182-154.eu-central-1.compute.amazonaws.com";
    public static final String PATH_ITEM = ":8081/sendEmail";

    private final RestTemplate restTemplate;

    public MailClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendEmail(MailDTO mailDTO) {
        HttpEntity<MailDTO> httpEntity = new HttpEntity<>(mailDTO);
        restTemplate.exchange(BASE_URL + PATH_ITEM, HttpMethod.POST, httpEntity, Void.class);
    }
}
