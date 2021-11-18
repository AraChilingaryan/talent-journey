package com.disqo.flow_manager_service.client;

import com.disqo.flow_manager_service.rest.dto.TalentDTO;
import com.disqo.flow_manager_service.rest.dto.UserDTO;
import com.disqo.flow_manager_service.rest.dto.UserServiceRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class UserClient {
    public static final String BASE_URL = "http://localhost:8080";
    public static final String PATH_ITEM = "/user";

    private final RestTemplate restTemplate;

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDTO getUser(Long id) {
        return restTemplate
                .getForEntity(BASE_URL + PATH_ITEM+ "/" + id, UserDTO.class)
                .getBody();
    }
    public UserDTO sendTalent(UserServiceRequest userServiceRequest){
       return restTemplate
                .postForEntity(BASE_URL+PATH_ITEM + "/register", userServiceRequest, UserDTO.class)
                .getBody();
    }
}
