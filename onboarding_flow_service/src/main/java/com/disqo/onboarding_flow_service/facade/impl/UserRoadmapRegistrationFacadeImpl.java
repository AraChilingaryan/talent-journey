//package com.disqo.onboarding_flow_service.facade.impl;
//
//import com.disqo.onboarding_flow_service.annotation.Facade;
//import com.disqo.onboarding_flow_service.client.jiraclient.JiraIntegrationClientFacade;
//import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.*;
//import com.disqo.onboarding_flow_service.client.jiraclient.sprint.dto.JiraSprintDto;
//import com.disqo.onboarding_flow_service.client.mailclient.MailSenderClient;
//import com.disqo.onboarding_flow_service.converter.MenteeConverter;
//import com.disqo.onboarding_flow_service.converter.MentorConverter;
//import com.disqo.onboarding_flow_service.converter.RoadmapConverter;
//import com.disqo.onboarding_flow_service.facade.UserRoadmapRegistrationFacade;
//import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
//import com.disqo.onboarding_flow_service.service.MenteeService;
//import com.disqo.onboarding_flow_service.service.MentorService;
//import com.disqo.onboarding_flow_service.service.RoadmapService;
//
//@Facade
//public class UserRoadmapRegistrationFacadeImpl implements UserRoadmapRegistrationFacade {
//
//    private final RoadmapService roadmapService;
//
//    @Override
//    public JiraSprintDto createSprint(JiraSprintDto jiraSprintDto) {
//        final Roadmap roadmap = roadmapService.findByProjectKey(jiraSprintDto.getProjectKey());
//        final ProjectBoardDto projectBoards = jiraIntegrationClientFacade
//                .getProjectBoards(roadmap.getJiraProjectKey(), roadmap.getName());
//        final Long boardId = projectBoards.getValues().getFirst().getId();
//        jiraSprintDto.setId(boardId);
//        return jiraIntegrationClientFacade.createSprint(jiraSprintDto);
//    }
//}
