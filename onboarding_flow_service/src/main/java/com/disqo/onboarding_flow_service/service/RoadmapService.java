package com.disqo.onboarding_flow_service.service;

import com.disqo.onboarding_flow_service.exception.RoadmapNotFoundException;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDTO;

import java.util.List;

public interface RoadmapService {

    List<Roadmap> findALl();

    Roadmap findById(Long id) throws RoadmapNotFoundException;

    Roadmap create(RoadmapDTO roadmapDTO);

    Roadmap update(Long id, RoadmapDTO roadmapDTO) throws RoadmapNotFoundException;

    boolean deleteById(Long id);

    Roadmap updateStatus(Long id, String status);

    Roadmap findByProjectKey(String projectKey);
}
