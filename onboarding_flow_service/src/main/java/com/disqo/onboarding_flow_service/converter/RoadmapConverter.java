package com.disqo.onboarding_flow_service.converter;

import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.ProjectRequestDto;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDto;

import java.util.List;

public interface RoadmapConverter {

    List<RoadmapDto> bulkConvertToDTO(List<Roadmap> roadmaps);

    RoadmapDto convertToDto(Roadmap roadmap);

    ProjectRequestDto convertToJiraProjectDTO(RoadmapDto roadmapDTO);

}
