package com.disqo.onboarding_flow_service.converter;

import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDTO;

import java.util.List;

public interface RoadmapConverter {

    List<RoadmapDTO> bulkConvertToDTO(List<Roadmap> roadmaps);

    RoadmapDTO convertToDTO(Roadmap roadmap);

    List<Roadmap> bulkConvertToEntity(List<RoadmapDTO> roadmaps);

    Roadmap convertToEntity(RoadmapDTO roadmapDTO);

}
