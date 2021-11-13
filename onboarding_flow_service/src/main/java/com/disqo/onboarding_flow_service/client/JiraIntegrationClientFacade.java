package com.disqo.onboarding_flow_service.client;

import com.disqo.onboarding_flow_service.client.project.dto.ProjectRequestDto;
import com.disqo.onboarding_flow_service.client.project.dto.ProjectResponseDto;
import com.disqo.onboarding_flow_service.client.user.dto.JiraUserDto;

public interface JiraIntegrationClientFacade {

    ProjectResponseDto createProject(ProjectRequestDto project);
  //  UserDto crateUser(UserDto userDto);

    ProjectResponseDto getProject(String projectKey);

    ProjectResponseDto getProjectRoles(String projectKey);

    JiraUserDto createUser(JiraUserDto user);

    void deleteUser(String accountId);



}
