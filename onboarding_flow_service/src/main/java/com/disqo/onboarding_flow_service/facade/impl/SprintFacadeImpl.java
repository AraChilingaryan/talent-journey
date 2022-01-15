package com.disqo.onboarding_flow_service.facade.impl;

import com.disqo.onboarding_flow_service.annotation.Facade;
import com.disqo.onboarding_flow_service.client.jiraclient.JiraIntegrationClientFacade;
import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.ProjectBoardDto;
import com.disqo.onboarding_flow_service.client.jiraclient.sprint.dto.JiraSprintDto;
import com.disqo.onboarding_flow_service.converter.SprintConverter;
import com.disqo.onboarding_flow_service.facade.SprintFacade;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.persistance.entity.Sprint;
import com.disqo.onboarding_flow_service.service.RoadmapService;
import com.disqo.onboarding_flow_service.service.SprintService;
import com.disqo.onboarding_flow_service.service.dto.SprintDto;
import lombok.RequiredArgsConstructor;

@Facade
@RequiredArgsConstructor
public class SprintFacadeImpl implements SprintFacade {

    private final JiraIntegrationClientFacade jiraClientFacade;
    private final SprintService sprintService;
    private final SprintConverter sprintConverter;
    private final JiraIntegrationClientFacade jiraIntegrationClientFacade;
    private final RoadmapService roadmapService;

    @Override
    public SprintDto create(final SprintDto sprintDto) {
        final Sprint sprint = sprintService.create(sprintDto);
        final SprintDto responseDto = this.sprintConverter.convertToDto(sprint);
        final JiraSprintDto jiraSprintDto = this.sprintConverter.convertToJiraDto(sprintDto);
        final ProjectBoardDto projectBoards = jiraIntegrationClientFacade
                .getProjectBoards(sprint.getRoadmap().getJiraProjectKey(), sprint.getRoadmap().getName());
        final Long boardId = projectBoards.getValues().getFirst().getId();
        jiraSprintDto.setId(boardId);
        jiraClientFacade.createSprint(jiraSprintDto);
        return responseDto;
    }
}
