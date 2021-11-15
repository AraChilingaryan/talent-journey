package com.disqo.onboarding_flow_service.converter;

import com.disqo.onboarding_flow_service.persistance.entity.Mentor;
import com.disqo.onboarding_flow_service.service.dto.MentorDTO;

import java.util.List;

public interface MentorConverter {

    List<MentorDTO> bulkConvertToDTO(List<Mentor> mentors);

    MentorDTO convertToDTO(Mentor mentor);

    List<Mentor> bulkConvertToEntity(List<MentorDTO> mentorDTOS);

    Mentor convertToEntity(MentorDTO mentorDTO);

}
