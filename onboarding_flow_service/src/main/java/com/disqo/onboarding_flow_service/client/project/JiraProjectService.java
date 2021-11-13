package com.disqo.onboarding_flow_service.client.project;

import com.disqo.onboarding_flow_service.client.project.dto.ProjectRequestDto;
import com.disqo.onboarding_flow_service.client.project.dto.ProjectResponseDto;

public interface JiraProjectService {

    ProjectResponseDto createProject(ProjectRequestDto project);

    ProjectResponseDto getProject(String projectKey);

    ProjectResponseDto getProjectRoles(String projectKey);



    //ProjectDto updateProject()
    //delete Project
}
