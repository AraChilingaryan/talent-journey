package com.disqo.onboarding_flow_service.converter.impl;

import com.disqo.onboarding_flow_service.annotation.Converter;
import com.disqo.onboarding_flow_service.converter.RoadmapConverter;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDTO;

import java.util.List;
import java.util.stream.Collectors;

@Converter
public class RoadmapConverterImpl implements RoadmapConverter {

    @Override
    public List<RoadmapDTO> bulkConvertToDTO(List<Roadmap> roadmaps) {
        return roadmaps.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    //TODO add converter body

    @Override
    public RoadmapDTO convertToDTO(Roadmap roadmap) {
        return null;
    }

    @Override
    public List<Roadmap> bulkConvertToEntity(List<RoadmapDTO> roadmaps) {
        return roadmaps.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    //TODO add converter body

    @Override
    public Roadmap convertToEntity(RoadmapDTO roadmapDTO) {
        return null;
    }
}
