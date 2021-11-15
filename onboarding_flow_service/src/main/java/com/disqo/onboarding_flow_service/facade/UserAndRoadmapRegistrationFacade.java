package com.disqo.onboarding_flow_service.facade;

import com.disqo.onboarding_flow_service.client.project.dto.AssignUserProjectRoleDto;
import com.disqo.onboarding_flow_service.client.project.dto.ProjectRoleResponseDto;
import com.disqo.onboarding_flow_service.service.dto.MenteeDTO;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDTO;

public interface UserAndRoadmapRegistrationFacade {

    MenteeDTO createMentee(MenteeDTO menteeDTO);

    RoadmapDTO createRoadmap(RoadmapDTO roadmapDTO);

    ProjectRoleResponseDto addUserToProject(String projectKey, Long mentorId, Long menteeId);



}
