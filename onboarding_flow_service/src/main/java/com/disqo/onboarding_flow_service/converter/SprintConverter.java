package com.disqo.onboarding_flow_service.converter;

import com.disqo.onboarding_flow_service.client.jiraclient.sprint.dto.JiraSprintDto;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.persistance.entity.Sprint;
import com.disqo.onboarding_flow_service.service.dto.SprintDto;

import java.util.List;


public interface SprintConverter {

    List<JiraSprintDto> bulkConvertToDTO(List<Sprint> sprints);

    SprintDto convertToDto(Sprint sprint);

    JiraSprintDto convertToJiraDto(SprintDto sprintDto);
}
