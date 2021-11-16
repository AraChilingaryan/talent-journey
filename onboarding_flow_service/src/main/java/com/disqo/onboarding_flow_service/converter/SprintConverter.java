package com.disqo.onboarding_flow_service.converter;

import com.disqo.onboarding_flow_service.annotation.Converter;
import com.disqo.onboarding_flow_service.client.jiraclient.sprint.dto.SprintDto;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.persistance.entity.Sprint;

import java.util.List;

@Converter
public interface SprintConverter {

    List<SprintDto> bulkConvertToDTO(List<Sprint> sprints);

    SprintDto convertToDTO(Sprint sprint);

    List<Sprint> bulkConvertToEntity(List<SprintDto> sprintDtos);

    Sprint convertToEntity(SprintDto sprintDto, Roadmap roadmap);

}
