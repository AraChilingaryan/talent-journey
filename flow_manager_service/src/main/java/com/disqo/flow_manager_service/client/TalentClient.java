package com.disqo.flow_manager_service.client;

import com.disqo.flow_manager_service.rest.dto.TalentDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class TalentClient {
    public static final String BASE_URL = "http://localhost:8083";
    public static final String PATH_ITEM = "/talent";

    private final RestTemplate restTemplate;

    public TalentClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public TalentDTO getTalent(String email) {
        return restTemplate
                .getForEntity(BASE_URL + PATH_ITEM + "/" + email, TalentDTO.class)
                .getBody();
    }

    public TalentDTO sendTalent( TalentDTO talentDTO) {
        HttpEntity<TalentDTO> req = new HttpEntity<>(talentDTO);
        return restTemplate
                .exchange(BASE_URL + PATH_ITEM + "/update/", HttpMethod.PUT, req, TalentDTO.class)
                .getBody();


    }
}
