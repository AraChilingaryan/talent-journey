package com.disqo.onboarding_flow_service.client.jiraclient.project;

import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.*;

public interface JiraProjectService {

    ProjectResponseDto createProject(ProjectRequestDto project);

    ProjectResponseDto getProject(String projectKey);

    ProjectRoleDto getProjectRoles(String projectKey);

    ProjectRoleResponseDto addUserToProject(String url,AssignUserProjectRoleDto user);

    ProjectBoardDto getProjectBoard(String projectId, String projectName);

    //ProjectDto updateProject()
    //delete Project
}
