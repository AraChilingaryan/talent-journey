package com.disqo.onboarding_flow_service.client.jiraclient.impl;

import com.disqo.onboarding_flow_service.annotation.Facade;
import com.disqo.onboarding_flow_service.client.jiraclient.JiraIntegrationClientFacade;
import com.disqo.onboarding_flow_service.client.jiraclient.project.JiraProjectService;
import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.*;
import com.disqo.onboarding_flow_service.client.jiraclient.sprint.JiraSprintService;
import com.disqo.onboarding_flow_service.client.jiraclient.sprint.dto.SprintDto;
import com.disqo.onboarding_flow_service.client.jiraclient.user.JiraUserService;
import com.disqo.onboarding_flow_service.client.jiraclient.user.dto.JiraUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Facade
public class JiraIntegrationClientFacadeImpl implements JiraIntegrationClientFacade {

    private final static Logger log = LoggerFactory.getLogger(JiraIntegrationClientFacadeImpl.class);

    private final JiraProjectService jiraProjectService;
    private final JiraUserService jiraUserService;
    private final JiraSprintService jiraSprintService;

    public JiraIntegrationClientFacadeImpl(final JiraProjectService jiraProjectService,
                                           final JiraUserService jiraUserService,
                                           final JiraSprintService jiraSprintService) {
        this.jiraProjectService = jiraProjectService;
        this.jiraUserService = jiraUserService;
        this.jiraSprintService = jiraSprintService;
    }

    @Override
    public ProjectResponseDto createProject(final ProjectRequestDto project) {
        log.info("In project create method");
        return this.jiraProjectService.createProject(project);
    }

    @Override
    public ProjectResponseDto getProject(final String projectKey) {
        return this.jiraProjectService.getProject(projectKey);
    }

    @Override
    public ProjectRoleDto getProjectRoles(final String projectKey) {
        return this.jiraProjectService.getProjectRoles(projectKey);
    }

    @Override
    public JiraUserDto createUser(final JiraUserDto user) {
        return jiraUserService.createUser(user);
    }

    @Override
    public void deleteUser(final String accountId) {
        this.jiraUserService.deleteUser(accountId);
    }

    @Override
    public ProjectRoleResponseDto addUserToProject(final String projectKey, final AssignUserProjectRoleDto user) {
        final ProjectRoleDto projectRoles = this.jiraProjectService.getProjectRoles(projectKey);
        final String url = projectRoles.getProjectRoleUrlFrom(user.getRole());
        return this.jiraProjectService.addUserToProject(url, user);
    }

    @Override
    public ProjectBoardDto getProjectBoards(final String projectId, final String projectName) {
        return this.jiraProjectService.getProjectBoard(projectId, projectName);
    }

    @Override
    public SprintDto createSprint(final SprintDto sprintDto) {
        return this.jiraSprintService.create(sprintDto);
    }

    @Override
    public void deleteSprint(final Long id) {
        this.jiraSprintService.delete(id);
    }
}
