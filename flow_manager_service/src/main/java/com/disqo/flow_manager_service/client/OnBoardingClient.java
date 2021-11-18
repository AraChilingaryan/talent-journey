package com.disqo.flow_manager_service.client;

import com.disqo.flow_manager_service.rest.dto.TalentDTO;
import com.disqo.flow_manager_service.rest.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OnBoardingClient {
    public static final String BASE_URL = "";
    public static final String PATH_ITEM = "/";

    private final RestTemplate restTemplate;

    public OnBoardingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public UserDTO sendUser(UserDTO userDTO){
        return restTemplate
                .postForEntity(BASE_URL+PATH_ITEM, userDTO, UserDTO.class)
                .getBody();

    }

}
