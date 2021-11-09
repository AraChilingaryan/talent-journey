package com.disqo.interview_flow_service.converter.impl;

import com.disqo.interview_flow_service.converter.*;
import com.disqo.interview_flow_service.persistance.entity.interview.Interview;
import com.disqo.interview_flow_service.persistance.enums.InterviewStatus;
import com.disqo.interview_flow_service.persistance.enums.InterviewType;
import com.disqo.interview_flow_service.persistance.repositories.SpecializationRepository;
import com.disqo.interview_flow_service.persistance.repositories.UserRepository;
import com.disqo.interview_flow_service.service.SpecializationService;
import com.disqo.interview_flow_service.service.dto.InterviewRequestDTO;
import com.disqo.interview_flow_service.service.dto.InterviewResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InterviewConverterImpl implements InterviewConverter {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private FeedbackConverter feedbackConverter;

    private final SpecializationService specializationService;
    private final UserRepository userRepository;
    private final TalentConverter talentConverter;
    private final SpecializationConverter specializationConverter;

    public InterviewConverterImpl(SpecializationService specializationService, UserRepository userRepository, TalentConverter talentConverter, SpecializationConverter specializationConverter) {
        this.specializationService = specializationService;
        this.userRepository = userRepository;
        this.talentConverter = talentConverter;
        this.specializationConverter = specializationConverter;
    }


    @Override
    public List<InterviewResponseDTO> bulkConvertToDTO(List<Interview> interviews) {
        return interviews.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    @Override
    public InterviewResponseDTO convertToDTO(Interview interview) {
        if (interview == null) {
            return null;
        }
        final InterviewResponseDTO interviewResponseDTO = new InterviewResponseDTO();
        interviewResponseDTO.setId(interview.getId());
        interviewResponseDTO.setDate(interview.getStartDate());
        interviewResponseDTO.setDate(interview.getEndDate());
        interviewResponseDTO.setInterviewType(InterviewType.valueOf(interview.getInterviewType().name()));
        interviewResponseDTO.setInterviewStatus(InterviewStatus.valueOf(interview.getInterviewStatus().name()));
        interviewResponseDTO.setTalentDTO(talentConverter.convertToDTO(interview.getTalent()));
        interviewResponseDTO.setUserDTO(userConverter.bulkConvertToDTO(interview.getUsers()));
        interviewResponseDTO.setInterviewFeedback(feedbackConverter.convertToDTO(interview.getInterviewFeedback()));
        interviewResponseDTO.setSpecialization(specializationConverter.convertToDTO(interview.getSpecialization()));

        return interviewResponseDTO;

    }

    @Override
    public List<Interview> bulkConvertToEntity(List<InterviewRequestDTO> interviews) {
        return interviews.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Interview convertToEntity(InterviewRequestDTO interviewRequestDTO) {
        final Interview interview = new Interview();
        interview.setUrl(URI.create(interviewRequestDTO.getCalendarURI()));
        interview.setTalent(talentConverter.convertToEntity(interviewRequestDTO.getTalentDTO()));
        interview.setUsers(userRepository.findAllById(interviewRequestDTO.getUserIDs()));
        interview.setSpecialization(specializationService.findById(interviewRequestDTO.getSpecializationId()));

        return interview;
    }
}
