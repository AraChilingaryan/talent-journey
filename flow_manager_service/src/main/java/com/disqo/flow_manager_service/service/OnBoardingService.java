package com.disqo.flow_manager_service.service;

import com.disqo.flow_manager_service.client.OnBoardingClient;
import com.disqo.flow_manager_service.rest.dto.UserDTO;
import com.disqo.flow_manager_service.rest.dto.UserServiceRequest;
import org.springframework.stereotype.Service;

@Service
public class OnBoardingService {
    private final OnBoardingClient onBoardingClient;

    public OnBoardingService(OnBoardingClient onBoardingClient) {
        this.onBoardingClient = onBoardingClient;
    }

    public UserDTO sendToOnBoard(UserDTO userDTO){
        return onBoardingClient.sendUser(userDTO);

    }
}
