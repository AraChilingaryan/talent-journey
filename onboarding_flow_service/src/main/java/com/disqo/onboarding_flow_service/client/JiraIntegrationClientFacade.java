package com.disqo.onboarding_flow_service.client;

import com.disqo.onboarding_flow_service.client.enums.RoleType;
import com.disqo.onboarding_flow_service.client.project.dto.*;
import com.disqo.onboarding_flow_service.client.user.dto.JiraUserDto;

public interface JiraIntegrationClientFacade {

    ProjectResponseDto createProject(ProjectRequestDto project);

    ProjectResponseDto getProject(String projectKey);

    ProjectRoleDto getProjectRoles(String projectKey);

    JiraUserDto createUser(JiraUserDto user);

    void deleteUser(String accountId);

    ProjectRoleResponseDto addUserToProject(String projectKey, AssignUserProjectRoleDto user);



}
