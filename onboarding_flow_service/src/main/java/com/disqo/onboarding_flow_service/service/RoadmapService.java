package com.disqo.onboarding_flow_service.service;

import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.ProjectResponseDto;
import com.disqo.onboarding_flow_service.exception.RoadmapNotFoundException;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDto;

import java.util.List;

public interface RoadmapService {

    List<Roadmap> findALl();

    Roadmap findById(Long id) throws RoadmapNotFoundException;

    Roadmap create(RoadmapDto roadmapDTO, ProjectResponseDto jiraProject);

    Roadmap update(Long id, RoadmapDto roadmapDTO) throws RoadmapNotFoundException;

    boolean deleteById(Long id);

    Roadmap updateStatus(Long id, String status);

    Roadmap findByProjectKey(String projectKey);
}
