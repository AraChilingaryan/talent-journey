package com.disqo.onboarding_flow_service.client.jiraclient.sprint;

import com.disqo.onboarding_flow_service.client.jiraclient.sprint.dto.SprintDto;

public interface JiraSprintService {

    SprintDto create(SprintDto sprint);

    void delete(Long id);

}
