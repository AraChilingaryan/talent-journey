package com.disqo.onboarding_flow_service.converter.impl;

import com.disqo.onboarding_flow_service.annotation.Converter;
import com.disqo.onboarding_flow_service.client.jiraclient.enums.AssigneeType;
import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.ProjectRequestDto;
import com.disqo.onboarding_flow_service.converter.RoadmapConverter;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.persistance.enums.RoadmapStatus;
import com.disqo.onboarding_flow_service.service.MenteeService;
import com.disqo.onboarding_flow_service.service.MentorService;
import com.disqo.onboarding_flow_service.service.RoadmapService;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDTO;
import com.disqo.onboarding_flow_service.service.enums.RoadmapStatusClientType;
import com.disqo.onboarding_flow_service.util.Util;

import java.util.List;
import java.util.stream.Collectors;

@Converter
public class RoadmapConverterImpl implements RoadmapConverter {

    private final MentorService mentorService;
    private final MenteeService menteeService;
    private final Util util;

    public RoadmapConverterImpl(MentorService mentorService, MenteeService menteeService, Util util) {
        this.mentorService = mentorService;
        this.menteeService = menteeService;
        this.util = util;
    }

    @Override
    public List<RoadmapDTO> bulkConvertToDTO(List<Roadmap> roadmaps) {
        return roadmaps.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public RoadmapDTO convertToDTO(Roadmap roadmap) {
        final RoadmapDTO roadmapDTO = new RoadmapDTO();
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
    public List<Roadmap> bulkConvertToEntity(List<RoadmapDTO> roadmaps) {
        return roadmaps.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Roadmap convertToEntity(RoadmapDTO roadmapDTO) {
        final Roadmap roadmap = new Roadmap();
        roadmap.setStartDate(roadmapDTO.getStartDate());
        roadmap.setEndDate(roadmapDTO.getEndDate());
        roadmap.setDescription(roadmapDTO.getDescription());
        roadmap.setName(roadmapDTO.getName());
        roadmap.setStatus(RoadmapStatus.valueOf(roadmapDTO.getStatus().name()));
        roadmap.setMentee(menteeService.findById(roadmapDTO.getMenteeId()));
        roadmap.setMentor(mentorService.findById(roadmapDTO.getMentorId()));
        return roadmap;
    }

    @Override
    public ProjectRequestDto convertToJiraProjectDTO(RoadmapDTO roadmapDTO) {
        final ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setDescription(roadmapDTO.getDescription());
        projectRequestDto.setLeadAccountId(mentorService.findById(roadmapDTO.getMentorId()).getAccountId());
        projectRequestDto.setProjectName(roadmapDTO.getName());
        projectRequestDto.setAssigneeType(AssigneeType.PROJECT_LEAD);
        projectRequestDto.setKey(util.generateJiraKeyFor(roadmapDTO));
        return projectRequestDto;
    }
}
