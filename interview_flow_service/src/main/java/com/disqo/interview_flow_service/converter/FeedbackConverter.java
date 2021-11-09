package com.disqo.interview_flow_service.converter;

import com.disqo.interview_flow_service.persistance.entity.interview.InterviewFeedback;
import com.disqo.interview_flow_service.service.dto.InterviewFeedbackRequestDTO;
import com.disqo.interview_flow_service.service.dto.InterviewFeedbackResponseDTO;


import java.util.List;

public interface FeedbackConverter {

    List<InterviewFeedbackResponseDTO> bulkConvertToDTO(List<InterviewFeedback> interviewFeedbacks);

    InterviewFeedbackResponseDTO convertToDTO(InterviewFeedback interviewFeedback);

    List<InterviewFeedback> bulkConvertToEntity(List<InterviewFeedbackRequestDTO> interviewFeedbacks);

    InterviewFeedback convertToEntity(InterviewFeedbackRequestDTO interviewFeedbackReqDTO);
}
