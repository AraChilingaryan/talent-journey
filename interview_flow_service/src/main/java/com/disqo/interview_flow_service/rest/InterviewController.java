package com.disqo.interview_flow_service.rest;

import com.disqo.interview_flow_service.persistance.entity.interview.Interview;
import com.disqo.interview_flow_service.service.InterviewService;
import com.disqo.interview_flow_service.service.dto.InterviewEventDTO;
import com.disqo.interview_flow_service.service.dto.InterviewRequestDTO;
import com.disqo.interview_flow_service.service.dto.InterviewResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/interview")
public class InterviewController {
    private final Logger log = LoggerFactory.getLogger(InterviewController.class);

    private final InterviewService interviewService;

    @Autowired
    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping
    public @ResponseBody
    String create(@RequestBody @Valid InterviewRequestDTO interviewRequestDTO) {
        log.info("In InterviewController, /interview ,create");
        return interviewService.preparationInterview(interviewRequestDTO);

    }

    @PutMapping
    public @ResponseBody
    String updateFromEvent(@RequestBody @Valid InterviewEventDTO eventDTO) {
        log.info("In InterviewController, /interview ,update");
        return interviewService.addEvent(eventDTO);
    }

    @GetMapping("/search/{talent_id}")
    public List<Interview> findAllInterviews(@PathVariable Long talent_id) {
        log.info("In InterviewController, /interview/search/{talent_id},search");
        return interviewService.findTalentAllInterviews(talent_id);
    }

}
