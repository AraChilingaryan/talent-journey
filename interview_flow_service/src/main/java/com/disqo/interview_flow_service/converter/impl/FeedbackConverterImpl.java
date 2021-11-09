package com.disqo.interview_flow_service.converter.impl;

import com.disqo.interview_flow_service.converter.InterviewConverter;
import com.disqo.interview_flow_service.converter.FeedbackConverter;
import com.disqo.interview_flow_service.persistance.entity.interview.InterviewFeedback;
import com.disqo.interview_flow_service.service.dto.InterviewFeedbackRequestDTO;
import com.disqo.interview_flow_service.service.dto.InterviewFeedbackResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FeedbackConverterImpl implements FeedbackConverter {

    @Autowired
    private InterviewConverter interviewConverter;

    @Override
    public List<InterviewFeedbackResponseDTO> bulkConvertToDTO(List<InterviewFeedback> interviewFeedbacks) {
        return interviewFeedbacks.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public InterviewFeedbackResponseDTO convertToDTO(InterviewFeedback interviewFeedback) {
        final InterviewFeedbackResponseDTO feedbackDTO = new InterviewFeedbackResponseDTO();

        feedbackDTO.setId(interviewFeedback.getId());
        feedbackDTO.setTopic(interviewFeedback.getTopic());
        feedbackDTO.setFeedback(interviewFeedback.getFeedback());
        feedbackDTO.setScore(interviewFeedback.getScore());
        return feedbackDTO;

    }

    @Override
    public List<InterviewFeedback> bulkConvertToEntity(List<InterviewFeedbackRequestDTO> interviewFeedbacks) {
        return interviewFeedbacks.stream().map(this::convertToEntity).collect(Collectors.toList());

    }

    @Override
    public InterviewFeedback convertToEntity(InterviewFeedbackRequestDTO interviewFeedbackReqDTO) {
        if (interviewFeedbackReqDTO == null) {
            return null;
        }
        final InterviewFeedback feedback = new InterviewFeedback();
        feedback.setTopic(interviewFeedbackReqDTO.getTopic());
        feedback.setFeedback(interviewFeedbackReqDTO.getFeedback());
        feedback.setScore(interviewFeedbackReqDTO.getScore());

        return feedback;
    }
}
