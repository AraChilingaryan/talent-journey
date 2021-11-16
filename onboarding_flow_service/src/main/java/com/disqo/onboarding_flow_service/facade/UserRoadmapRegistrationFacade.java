package com.disqo.onboarding_flow_service.facade;

import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.ProjectRoleResponseDto;
import com.disqo.onboarding_flow_service.client.jiraclient.sprint.dto.SprintDto;
import com.disqo.onboarding_flow_service.service.dto.MenteeDTO;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDTO;

public interface UserRoadmapRegistrationFacade {

    MenteeDTO createMentee(MenteeDTO menteeDTO);

    RoadmapDTO createRoadmap(RoadmapDTO roadmapDTO);

    ProjectRoleResponseDto addUserToProject(String projectKey, Long mentorId, Long menteeId);

    SprintDto createSprint(SprintDto sprintDto);



}
