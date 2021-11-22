package com.disqo.onboarding_flow_service.converter;

import com.disqo.onboarding_flow_service.persistance.entity.Mentor;
import com.disqo.onboarding_flow_service.service.dto.MentorDto;

import java.util.List;

public interface MentorConverter {

    List<MentorDto> bulkConvertToDTO(List<Mentor> mentors);

    MentorDto convertToDTO(Mentor mentor);

    List<Mentor> bulkConvertToEntity(List<MentorDto> mentorDtos);

    Mentor convertToEntity(MentorDto mentorDTO);

}
