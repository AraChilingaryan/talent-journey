package com.disqo.onboarding_flow_service.converter;

import com.disqo.onboarding_flow_service.client.jiraclient.user.dto.JiraUserDto;
import com.disqo.onboarding_flow_service.persistance.entity.Mentee;
import com.disqo.onboarding_flow_service.service.dto.MenteeDto;

import java.util.List;

public interface MenteeConverter {

    List<MenteeDto> bulkConvertToDto(List<Mentee> mentees);

    MenteeDto convertToDto(Mentee mentee);

    JiraUserDto convertToJiraDTO (MenteeDto menteeDTO);

}
