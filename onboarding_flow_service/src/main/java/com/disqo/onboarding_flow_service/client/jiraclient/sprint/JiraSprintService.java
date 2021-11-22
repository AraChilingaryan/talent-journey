package com.disqo.onboarding_flow_service.client.jiraclient.sprint;

import com.disqo.onboarding_flow_service.client.jiraclient.sprint.dto.JiraSprintDto;

public interface JiraSprintService {

    JiraSprintDto create(JiraSprintDto sprint);

    void delete(Long id);

}
