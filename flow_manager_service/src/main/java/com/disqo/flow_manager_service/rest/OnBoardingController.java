package com.disqo.flow_manager_service.rest;

import com.disqo.flow_manager_service.client.OnBoardingClient;
import com.disqo.flow_manager_service.rest.dto.UserDTO;
import com.disqo.flow_manager_service.service.OnBoardingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/onboarding")
public class OnBoardingController {
    private final OnBoardingClient onBoardingClient;
    private final OnBoardingService onBoardingService;

    public OnBoardingController(OnBoardingClient onBoardingClient, OnBoardingService onBoardingService) {
        this.onBoardingClient = onBoardingClient;
        this.onBoardingService = onBoardingService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> sendOnboard(@RequestBody UserDTO userdto){
        return ResponseEntity.ok(onBoardingService.sendToOnBoard(userdto));

    }
}
