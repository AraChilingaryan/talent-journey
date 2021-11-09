package com.disqo.interview_flow_service.service;

import com.disqo.interview_flow_service.excaption.TalentNotFoundException;
import com.disqo.interview_flow_service.persistance.entity.interview.Interview;
import com.disqo.interview_flow_service.service.dto.InterviewEventDTO;
import com.disqo.interview_flow_service.service.dto.InterviewRequestDTO;

import java.util.List;

public interface InterviewService {
    String  preparationInterview(InterviewRequestDTO interviewRequestDTO);

    String  addEvent(InterviewEventDTO eventDTO);

    List<Interview> findALl();

    List<Interview> findTalentAllInterviews(Long id) throws TalentNotFoundException;

}
