package com.disqo.onboarding_flow_service.facade.impl;

import com.disqo.onboarding_flow_service.annotation.Facade;
import com.disqo.onboarding_flow_service.client.jiraclient.JiraIntegrationClientFacade;
import com.disqo.onboarding_flow_service.converter.SprintConverter;
import com.disqo.onboarding_flow_service.facade.SprintFacade;
import com.disqo.onboarding_flow_service.service.SprintService;
import com.disqo.onboarding_flow_service.service.dto.SprintDto;

@Facade
public class SprintFacadeImpl implements SprintFacade {

    private final JiraIntegrationClientFacade jiraClientFacade;
    private final SprintService sprintService;
    private final SprintConverter sprintConverter;

    public SprintFacadeImpl(final JiraIntegrationClientFacade jiraClientFacade,
                            final SprintService sprintService,
                            final SprintConverter sprintConverter) {
        this.jiraClientFacade = jiraClientFacade;
        this.sprintService = sprintService;
        this.sprintConverter = sprintConverter;
    }

    @Override
    public SprintDto create(final SprintDto sprintDto) {
        return this.sprintConverter.convertToDto(sprintService.create(sprintDto));
    }

    @Override
    public void delete(final Long id) {

    }
}
