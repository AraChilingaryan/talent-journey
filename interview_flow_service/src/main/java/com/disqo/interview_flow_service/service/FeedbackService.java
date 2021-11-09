package com.disqo.interview_flow_service.service;

import com.disqo.interview_flow_service.persistance.enums.InterviewType;
import com.disqo.interview_flow_service.service.dto.InterviewFeedbackRequestDTO;
import com.disqo.interview_flow_service.service.dto.InterviewFeedbackResponseDTO;

public interface FeedbackService {

    InterviewFeedbackResponseDTO create(InterviewFeedbackRequestDTO feedbackRequestDTO, InterviewType interviewType, Long talent_id);

}
