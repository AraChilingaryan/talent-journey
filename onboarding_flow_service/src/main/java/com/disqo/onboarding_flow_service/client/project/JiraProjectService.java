package com.disqo.onboarding_flow_service.client.project;

import com.disqo.onboarding_flow_service.client.project.dto.ProjectRequestDto;
import com.disqo.onboarding_flow_service.client.project.dto.ProjectResponseDto;
import com.disqo.onboarding_flow_service.client.project.dto.ProjectRoleResponseDto;

public interface JiraProjectService {

    ProjectResponseDto createProject(ProjectRequestDto project);

    ProjectResponseDto getProject(String projectKey);

    ProjectResponseDto getProjectRoles(String projectKey);

    ProjectRoleResponseDto addUserToProject(String projectKey,int roleId);



    //ProjectDto updateProject()
    //delete Project
}
