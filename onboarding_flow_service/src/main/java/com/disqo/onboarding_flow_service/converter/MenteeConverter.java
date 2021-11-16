package com.disqo.onboarding_flow_service.converter;

import com.disqo.onboarding_flow_service.client.jiraclient.user.dto.JiraUserDto;
import com.disqo.onboarding_flow_service.persistance.entity.Mentee;
import com.disqo.onboarding_flow_service.service.dto.MenteeDTO;

import java.util.List;

public interface MenteeConverter {

    List<MenteeDTO> bulkConvertToDTO(List<Mentee> mentees);

    MenteeDTO convertToDTO(Mentee mentee);

    List<Mentee> bulkConvertToEntity(List<MenteeDTO> menteeDTOS);

    Mentee convertToEntity(MenteeDTO menteeDTO);

    JiraUserDto convertToJiraDTO (MenteeDTO menteeDTO);

}