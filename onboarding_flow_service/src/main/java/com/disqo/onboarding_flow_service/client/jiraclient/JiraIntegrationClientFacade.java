package com.disqo.onboarding_flow_service.client.jiraclient;

import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.*;
import com.disqo.onboarding_flow_service.client.jiraclient.sprint.dto.JiraSprintDto;
import com.disqo.onboarding_flow_service.client.jiraclient.user.dto.JiraUserDto;

public interface JiraIntegrationClientFacade {

    ProjectResponseDto createProject(ProjectRequestDto project);

    ProjectResponseDto getProject(String projectKey);

    ProjectRoleDto getProjectRoles(String projectKey);

    JiraUserDto createUser(JiraUserDto user);

    void deleteUser(String accountId);

    ProjectRoleResponseDto addUserToProject(String projectKey, AssignUserProjectRoleDto user);

    ProjectBoardDto getProjectBoards(String projectId, String projectName);

    JiraSprintDto createSprint(JiraSprintDto jiraSprintDto);

    void deleteSprint(Long id);

}
