package com.disqo.flow_manager_service.rest;

import com.disqo.flow_manager_service.converter.Convert;
import com.disqo.flow_manager_service.converter.impl.ConvertImpl;
import com.disqo.flow_manager_service.rest.dto.InterviewClientRequest;
import com.disqo.flow_manager_service.rest.dto.TalentDTO;
import com.disqo.flow_manager_service.service.OnBoardingService;
import com.disqo.flow_manager_service.service.TalentService;
import com.disqo.flow_manager_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flowmanager")
public class FlowManagerController {

    private final TalentService talentService;
    private final UserService userService;
    private final Convert convert;
    private final OnBoardingService onBoardingService;

    @Autowired
    public FlowManagerController(TalentService talentService, UserService userService, Convert convert, OnBoardingService onBoardingService) {
        this.talentService = talentService;
        this.userService = userService;
        this.convert = convert;
        this.onBoardingService = onBoardingService;
    }

    @PostMapping("/start")
    public ResponseEntity<TalentDTO> firstProcess(@RequestBody TalentDTO talentDTO) {
        return ResponseEntity.ok(talentDTO);
    }

    @PostMapping
    public void secondProcces(@RequestBody InterviewClientRequest interviewClientRequest) {
        talentService
                .sendTalent(convert
                        .convertToTalentDTO(interviewClientRequest));

        userService
                .createUser(convert.
                        convertToUserDTO(interviewClientRequest));


        onBoardingService
                .sendToOnBoard(userService
                        .createUser(convert.
                                convertToUserDTO(interviewClientRequest)));

    }

}
