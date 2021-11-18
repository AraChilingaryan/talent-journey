package com.disqo.flow_manager_service.rest;

import com.disqo.flow_manager_service.client.TalentClient;
import com.disqo.flow_manager_service.client.UserClient;
import com.disqo.flow_manager_service.rest.dto.TalentDTO;
import com.disqo.flow_manager_service.rest.dto.UserDTO;
import com.disqo.flow_manager_service.rest.dto.UserServiceRequest;
import com.disqo.flow_manager_service.service.TalentService;
import com.disqo.flow_manager_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userclient")
public class UserController {
    private final UserClient userClient;
    private final UserService userService;

    public UserController(UserClient userClient, UserService userService) {
        this.userClient = userClient;
        this.userService = userService;
    }


    @GetMapping
    public UserDTO getUser() {
        return userService.getUser(7L);
    }

    @PostMapping
    public ResponseEntity<UserDTO> sendTalent(@RequestBody UserServiceRequest userServiceRequest){
        return ResponseEntity.ok(userService.createUser(userServiceRequest));
    }
}
