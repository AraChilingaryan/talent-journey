package com.disqo.onboarding_flow_service.facade.impl;

import com.disqo.onboarding_flow_service.annotation.Facade;
import com.disqo.onboarding_flow_service.client.jiraclient.JiraIntegrationClientFacade;
import com.disqo.onboarding_flow_service.client.jiraclient.enums.RoleType;
import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.AssignUserProjectRoleDto;
import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.ProjectRequestDto;
import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.ProjectResponseDto;
import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.ProjectRoleResponseDto;
import com.disqo.onboarding_flow_service.client.jiraclient.user.dto.JiraUserDto;
import com.disqo.onboarding_flow_service.client.mailclient.MailGenerator;
import com.disqo.onboarding_flow_service.client.mailclient.MailSenderClient;
import com.disqo.onboarding_flow_service.converter.MenteeConverter;
import com.disqo.onboarding_flow_service.converter.MentorConverter;
import com.disqo.onboarding_flow_service.converter.RoadmapConverter;
import com.disqo.onboarding_flow_service.facade.UserRoadmapRegistrationFacade;
import com.disqo.onboarding_flow_service.persistance.entity.Mentee;
import com.disqo.onboarding_flow_service.persistance.entity.Mentor;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.service.MenteeService;
import com.disqo.onboarding_flow_service.service.MentorService;
import com.disqo.onboarding_flow_service.service.RoadmapService;
import com.disqo.onboarding_flow_service.service.dto.MenteeDTO;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDTO;

import java.util.ArrayList;
import java.util.List;

@Facade
public class UserRoadmapRegistrationFacadeImpl implements UserRoadmapRegistrationFacade {

    private final JiraIntegrationClientFacade jiraIntegrationClientFacade;
    private final MenteeService menteeService;
    private final MentorService mentorService;
    private final RoadmapService roadmapService;
    private final MenteeConverter menteeConverter;
    private final MentorConverter mentorConverter;
    private final RoadmapConverter roadmapConverter;
    private final MailSenderClient mailSenderClient;

    public UserRoadmapRegistrationFacadeImpl(MenteeService menteeService, MentorService mentorService,
                                                JiraIntegrationClientFacade jiraIntegrationClientFacade,
                                                MenteeConverter menteeConverter, MentorConverter mentorConverter,
                                                RoadmapService roadmapService, RoadmapConverter roadmapConverter,
                                                MailSenderClient mailSenderClient) {
        this.menteeService = menteeService;
        this.mentorService = mentorService;
        this.jiraIntegrationClientFacade = jiraIntegrationClientFacade;
        this.menteeConverter = menteeConverter;
        this.mentorConverter = mentorConverter;
        this.roadmapService = roadmapService;
        this.roadmapConverter = roadmapConverter;
        this.mailSenderClient = mailSenderClient;
    }

    @Override
    public MenteeDTO createMentee(MenteeDTO menteeDTO) {
        final JiraUserDto jiraUserDto = menteeConverter.convertToJiraDTO(menteeDTO);
        final JiraUserDto responseDTO = jiraIntegrationClientFacade.createUser(jiraUserDto);
        menteeDTO.setAccountId(responseDTO.getAccountId());
        final Mentee mentee = menteeService.create(menteeDTO);
        mailSenderClient.sendEmail(MailGenerator.firstMeetingMailGenerator(mentee));
        return menteeConverter.convertToDTO(mentee);
    }

    @Override
    public RoadmapDTO createRoadmap(RoadmapDTO roadmapDTO) {
        final ProjectRequestDto projectRequestDto = roadmapConverter.convertToJiraProjectDTO(roadmapDTO);
        final ProjectResponseDto projectResponseDto = jiraIntegrationClientFacade.createProject(projectRequestDto);
        roadmapDTO.setJiraProjectKey(projectResponseDto.getKey());
        final Roadmap roadmap = roadmapService.create(roadmapDTO);
        return roadmapConverter.convertToDTO(roadmap);
    }

    @Override
    public ProjectRoleResponseDto addUserToProject(String projectKey, Long mentorId, Long menteeId){
        final Mentee mentee = menteeService.findById(menteeId);
        final Mentor mentor = mentorService.findById(mentorId);
        List<String> displayNames = new ArrayList<>();
        displayNames.add(mentee.getDisplayName());
        displayNames.add(mentor.getDisplayName());
        final RoleType role = RoleType.MEMBER;
        final AssignUserProjectRoleDto dto = new AssignUserProjectRoleDto(displayNames, role);
        final ProjectRoleResponseDto projectRoleResponseDto =
                jiraIntegrationClientFacade.addUserToProject(projectKey, dto);
        return projectRoleResponseDto;
    }


}
