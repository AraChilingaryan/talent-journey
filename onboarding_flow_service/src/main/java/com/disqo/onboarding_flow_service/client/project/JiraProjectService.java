package com.disqo.onboarding_flow_service.client.project;

import com.disqo.onboarding_flow_service.client.project.dto.*;

public interface JiraProjectService {

    ProjectResponseDto createProject(ProjectRequestDto project);

    ProjectResponseDto getProject(String projectKey);

    ProjectRoleDto getProjectRoles(String projectKey);

    ProjectRoleResponseDto addUserToProject(String url,AssignUserProjectRoleDto user);

    //ProjectDto updateProject()
    //delete Project
}
