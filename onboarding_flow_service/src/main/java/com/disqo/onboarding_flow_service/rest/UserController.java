package com.disqo.onboarding_flow_service.rest;

import com.disqo.onboarding_flow_service.facade.UserRoadmapRegistrationFacade;
import com.disqo.onboarding_flow_service.service.dto.MenteeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserRoadmapRegistrationFacade userRoadmapRegistrationFacade;

    public UserController(UserRoadmapRegistrationFacade userRoadmapRegistrationFacade) {
        this.userRoadmapRegistrationFacade = userRoadmapRegistrationFacade;
    }


    @PostMapping
    public ResponseEntity<MenteeDTO> createMentee(@RequestBody MenteeDTO menteeDTO) {
        return ResponseEntity.ok(this.userRoadmapRegistrationFacade.createMentee(menteeDTO));
    }

}
