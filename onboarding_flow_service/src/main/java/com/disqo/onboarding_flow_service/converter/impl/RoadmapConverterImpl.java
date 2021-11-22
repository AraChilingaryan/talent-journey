package com.disqo.onboarding_flow_service.converter.impl;

import com.disqo.onboarding_flow_service.annotation.Converter;
import com.disqo.onboarding_flow_service.client.jiraclient.enums.AssigneeType;
import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.ProjectRequestDto;
import com.disqo.onboarding_flow_service.config.JiraIntegrationProperties;
import com.disqo.onboarding_flow_service.converter.RoadmapConverter;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.service.MentorService;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDto;
import com.disqo.onboarding_flow_service.service.enums.RoadmapStatusClientType;

import java.util.List;
import java.util.stream.Collectors;

@Converter
public class RoadmapConverterImpl implements RoadmapConverter {

    private final MentorService mentorService;
    private final JiraIntegrationProperties properties;

    public RoadmapConverterImpl(final MentorService mentorService, JiraIntegrationProperties properties) {
        this.mentorService = mentorService;
        this.properties = properties;
    }

    @Override
    public List<RoadmapDto> bulkConvertToDTO(List<Roadmap> roadmaps) {
        return roadmaps.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public RoadmapDto convertToDto(Roadmap roadmap) {
        final RoadmapDto roadmapDTO = new RoadmapDto();
        roadmapDTO.setStartDate(roadmap.getStartDate());
        roadmapDTO.setEndDate(roadmap.getEndDate());
        roadmapDTO.setDescription(roadmap.getDescription());
        roadmapDTO.setName(roadmap.getName());
        roadmapDTO.setStatus(RoadmapStatusClientType.valueOf(roadmap.getStatus().name()));
        roadmapDTO.setMenteeId(roadmap.getMentee().getId());
        roadmapDTO.setMentorId(roadmap.getMentor().getId());
        return roadmapDTO;
    }

    @Override
    public ProjectRequestDto convertToJiraProjectDTO(RoadmapDto roadmapDTO) {
        final ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setDescription(roadmapDTO.getDescription());
        projectRequestDto.setLeadAccountId(mentorService.findById(roadmapDTO.getMentorId()).getAccountId());
        projectRequestDto.setProjectName(roadmapDTO.getName());
        projectRequestDto.setAssigneeType(AssigneeType.PROJECT_LEAD);
        projectRequestDto.setKey(generateJiraKeyFor(roadmapDTO));
        projectRequestDto.setProjectTemplateKey(properties.getProjectTemplateKey());
        return projectRequestDto;
    }

    private String generateJiraKeyFor(RoadmapDto roadmapDTO) {
        String name = roadmapDTO.getName();
        return ("" + name.charAt(0) + name.charAt(name.length() - 1)).toUpperCase();
    }
}
