package com.disqo.onboarding_flow_service.facade.impl;

import com.disqo.onboarding_flow_service.annotation.Facade;
import com.disqo.onboarding_flow_service.client.jiraclient.JiraIntegrationClientFacade;
import com.disqo.onboarding_flow_service.client.jiraclient.user.dto.JiraUserDto;
import com.disqo.onboarding_flow_service.converter.MenteeConverter;
import com.disqo.onboarding_flow_service.facade.MenteeFacade;
import com.disqo.onboarding_flow_service.persistance.entity.Mentee;
import com.disqo.onboarding_flow_service.service.MenteeService;
import com.disqo.onboarding_flow_service.service.dto.MenteeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Facade
public class MenteeFacadeImpl implements MenteeFacade {

    private final static Logger log = LoggerFactory.getLogger(MenteeFacadeImpl.class);

    private final JiraIntegrationClientFacade jiraClientFacade;
    private final MenteeService menteeService;
    private final MenteeConverter menteeConverter;

    public MenteeFacadeImpl(final JiraIntegrationClientFacade jiraClientFacade,
                            final MenteeService menteeService,
                            final MenteeConverter menteeConverter) {
        this.jiraClientFacade = jiraClientFacade;
        this.menteeService = menteeService;
        this.menteeConverter = menteeConverter;
    }

    @Override
    public MenteeDto create(final MenteeDto menteeDto) {
        log.info("Start mentee creating");
        final JiraUserDto jiraUser = this.jiraClientFacade.createUser(
                new JiraUserDto(menteeDto.getEmail(), menteeDto.getDisplayName()));
        final Mentee mentee = this.menteeService.create(menteeDto, jiraUser);
        log.info("Finished mentee creating");
        return this.menteeConverter.convertToDto(mentee);
    }

    @Override
    public MenteeDto findById(final Long id) {
        final Mentee mentee = this.menteeService.findById(id);
        return this.menteeConverter.convertToDto(mentee);
    }

    @Override
    public List<MenteeDto> getAll() {
        return this.menteeConverter.bulkConvertToDto(this.menteeService.findALl());
    }

    @Override
    public boolean delete(final Long id) {
        return this.menteeService.deleteById(id);
    }
}
