package com.disqo.interview_flow_service.controller;

import com.disqo.interview_flow_service.persistance.enums.InterviewType;
import com.disqo.interview_flow_service.service.FeedbackService;
import com.disqo.interview_flow_service.service.dto.InterviewFeedbackRequestDTO;
import com.disqo.interview_flow_service.service.dto.InterviewFeedbackResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/feedback")
@Slf4j
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public @ResponseBody InterviewFeedbackResponseDTO create(@RequestBody @Valid InterviewFeedbackRequestDTO feedbackRequestDTO,
                                            @RequestParam InterviewType interviewType, @RequestParam Long talentId) {
        return feedbackService.create(feedbackRequestDTO,interviewType, talentId);
    }

}
