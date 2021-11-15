package com.disqo.onboarding_flow_service.converter;

import com.disqo.onboarding_flow_service.persistance.entity.Mentee;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.service.dto.MenteeDTO;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDTO;

import java.util.List;

public interface MenteeConverter {

    List<MenteeDTO> bulkConvertToDTO(List<Mentee> mentees);

    MenteeDTO convertToDTO(Mentee mentee);

    List<Mentee> bulkConvertToEntity(List<MenteeDTO> menteeDTOS);

    Mentee convertToEntity(MenteeDTO menteeDTO);

}
