package com.disqo.flow_manager_service.client;

import com.disqo.flow_manager_service.rest.dto.InterviewRequest;
import com.disqo.flow_manager_service.rest.dto.TalentDTO;
import com.disqo.flow_manager_service.rest.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InterviewClient {
    public static final String BASE_URL = "http://localhost:8080";
    public static final String PATH_ITEM = "/interview";

    private final RestTemplate restTemplate;

    public InterviewClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TalentDTO getTalent (String email) {
        return restTemplate
                .getForEntity(BASE_URL + PATH_ITEM + "/"+ email, TalentDTO.class)
                .getBody();
    }
    public void sendTalent(InterviewRequest interviewRequest){
        restTemplate
                .postForEntity(BASE_URL+PATH_ITEM, interviewRequest, String.class)
                .getBody();

    }

}
