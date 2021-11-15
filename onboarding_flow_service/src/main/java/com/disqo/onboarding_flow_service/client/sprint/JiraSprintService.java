package com.disqo.onboarding_flow_service.client.sprint;

import com.disqo.onboarding_flow_service.client.sprint.dto.SprintDto;

public interface JiraSprintService {

    SprintDto create(SprintDto sprint);

    void delete(Long id);

}
