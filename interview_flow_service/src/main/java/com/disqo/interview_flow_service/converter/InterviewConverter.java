package com.disqo.interview_flow_service.converter;

import com.disqo.interview_flow_service.persistance.entity.interview.Interview;
import com.disqo.interview_flow_service.service.dto.InterviewRequestDTO;
import com.disqo.interview_flow_service.service.dto.InterviewResponseDTO;

import java.util.List;

public interface InterviewConverter {

    List<InterviewResponseDTO> bulkConvertToDTO(List<Interview> interviews);

    InterviewResponseDTO convertToDTO(Interview interview);

    List<Interview> bulkConvertToEntity(List<InterviewRequestDTO> interviews);

    Interview convertToEntity(InterviewRequestDTO interviewRequestDTO);
}
