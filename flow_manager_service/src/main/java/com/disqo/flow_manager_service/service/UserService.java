package com.disqo.flow_manager_service.service;

import com.disqo.flow_manager_service.client.TalentClient;
import com.disqo.flow_manager_service.client.UserClient;
import com.disqo.flow_manager_service.rest.dto.TalentDTO;
import com.disqo.flow_manager_service.rest.dto.UserDTO;
import com.disqo.flow_manager_service.rest.dto.UserServiceRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserClient userClient;

    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public UserDTO getUser(Long id) {
        return userClient.getUser(id);
    }

    public UserDTO createUser(UserServiceRequest userServiceRequest){
        return userClient.sendTalent(userServiceRequest);

    }


}
