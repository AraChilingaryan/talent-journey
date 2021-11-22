package com.disqo.onboarding_flow_service.facade;

import com.disqo.onboarding_flow_service.service.dto.RoadmapDto;

import java.util.List;

public interface RoadMapFacade {

    RoadmapDto create(RoadmapDto roadmapDto);

    boolean delete(Long id);

    RoadmapDto getById(Long id);

    List<RoadmapDto> getAll();

    RoadmapDto update(Long id, RoadmapDto roadmapDto);

}
