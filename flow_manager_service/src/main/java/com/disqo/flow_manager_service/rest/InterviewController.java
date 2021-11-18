package com.disqo.flow_manager_service.rest;

import com.disqo.flow_manager_service.client.InterviewClient;
import com.disqo.flow_manager_service.rest.dto.InterviewRequest;
import com.disqo.flow_manager_service.rest.dto.TalentDTO;
import com.disqo.flow_manager_service.rest.dto.UserDTO;
import com.disqo.flow_manager_service.service.InterviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interview")
public class InterviewController {
    private final InterviewClient interviewClient;
    private final InterviewService interviewService;

    public InterviewController(InterviewClient interviewClient, InterviewService interviewService) {
        this.interviewClient = interviewClient;
        this.interviewService = interviewService;
    }

    @GetMapping("/{email}")
    public TalentDTO getTalent(@PathVariable String email) {
        return interviewService.getTalent(email);
    }

   @PostMapping
   public @ResponseBody String sendTalent(@RequestBody InterviewRequest interviewRequest){
        interviewService.sendTalentToInterview(interviewRequest);
        return "ok";
   }
}