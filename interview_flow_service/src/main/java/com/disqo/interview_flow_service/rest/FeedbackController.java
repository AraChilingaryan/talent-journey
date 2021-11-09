package com.disqo.interview_flow_service.rest;

import com.disqo.interview_flow_service.persistance.enums.InterviewType;
import com.disqo.interview_flow_service.service.FeedbackService;
import com.disqo.interview_flow_service.service.dto.InterviewFeedbackRequestDTO;
import com.disqo.interview_flow_service.service.dto.InterviewFeedbackResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final Logger log = LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public @ResponseBody
    InterviewFeedbackResponseDTO create(@RequestBody @Valid InterviewFeedbackRequestDTO feedbackRequestDTO,
                                            @RequestParam InterviewType interviewType, @RequestParam Long talent_id) {
        log.info("In FeedbackController, /feedback ,create");
        return feedbackService.create(feedbackRequestDTO,interviewType,talent_id);
    }

}
