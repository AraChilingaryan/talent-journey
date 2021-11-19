package com.disqo.onboarding_flow_service.facade.impl;

import com.disqo.onboarding_flow_service.annotation.Facade;
import com.disqo.onboarding_flow_service.client.jiraclient.JiraIntegrationClientFacade;
import com.disqo.onboarding_flow_service.client.jiraclient.enums.RoleType;
import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.AssignUserProjectRoleDto;
import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.ProjectRequestDto;
import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.ProjectResponseDto;
import com.disqo.onboarding_flow_service.converter.RoadmapConverter;
import com.disqo.onboarding_flow_service.facade.RoadMapFacade;
import com.disqo.onboarding_flow_service.persistance.entity.Mentee;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.persistance.enums.RoadmapStatus;
import com.disqo.onboarding_flow_service.service.MenteeService;
import com.disqo.onboarding_flow_service.service.RoadmapService;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Facade
public class RoadMapFacadeImpl implements RoadMapFacade {

    private final static Logger log = LoggerFactory.getLogger(RoadMapFacadeImpl.class);

    private final JiraIntegrationClientFacade jiraClientFacade;
    private final RoadmapConverter roadmapConverter;
    private final RoadmapService roadmapService;

    public RoadMapFacadeImpl(final JiraIntegrationClientFacade jiraClientFacade,
                             final RoadmapConverter roadmapConverter,
                             final RoadmapService roadmapService) {
        this.jiraClientFacade = jiraClientFacade;
        this.roadmapConverter = roadmapConverter;
        this.roadmapService = roadmapService;
    }

    @Override
    public RoadmapDto create(final RoadmapDto roadmapDto) {
        log.info("In create roadMap");
        final ProjectRequestDto projectRequestDto = roadmapConverter.convertToJiraProjectDTO(roadmapDto);
        final ProjectResponseDto projectResponseDto = jiraClientFacade.createProject(projectRequestDto);
        final Roadmap roadmap = roadmapService.create(roadmapDto, projectResponseDto);
        roadmap.setStatus(RoadmapStatus.STARTED);

        final AssignUserProjectRoleDto userProjectRole = new AssignUserProjectRoleDto();
        userProjectRole.setRole(RoleType.MEMBER);
        userProjectRole.setUser(List.of(roadmap.getMentee().getAccountId()));

        this.jiraClientFacade.addUserToProject(roadmap.getJiraProjectKey(), userProjectRole);
        return roadmapConverter.convertToDto(roadmap);
    }

    @Override
    public boolean delete(final Long id) {
        return this.roadmapService.deleteById(id);
    }

    @Override
    public RoadmapDto getById(final Long id) {
        return this.roadmapConverter.convertToDto(this.roadmapService.findById(id));
    }

    @Override
    public List<RoadmapDto> getAll() {
        return this.roadmapConverter.bulkConvertToDTO(this.roadmapService.findALl());
    }

    @Override
    public RoadmapDto update(final Long id, final RoadmapDto roadmapDto) {
        return this.roadmapConverter.convertToDto(this.roadmapService.update(id, roadmapDto));
    }
}
