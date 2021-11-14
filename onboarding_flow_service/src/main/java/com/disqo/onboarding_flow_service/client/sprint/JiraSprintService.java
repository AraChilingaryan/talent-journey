package com.disqo.onboarding_flow_service.client.sprint;

import com.disqo.onboarding_flow_service.client.sprint.dto.SprintDto;

public interface JiraSprintService {

    SprintDto createSprint(SprintDto sprint);

    //SprintDto delete();
}
