package com.disqo.onboarding_flow_service.facade.impl;

import com.disqo.onboarding_flow_service.annotation.Facade;
import com.disqo.onboarding_flow_service.client.jiraclient.JiraIntegrationClientFacade;
import com.disqo.onboarding_flow_service.client.jiraclient.user.dto.JiraUserDto;
import com.disqo.onboarding_flow_service.client.mailclient.MailSenderClient;
import com.disqo.onboarding_flow_service.client.mailclient.dto.MailDTO;
import com.disqo.onboarding_flow_service.converter.MenteeConverter;
import com.disqo.onboarding_flow_service.converter.MentorConverter;
import com.disqo.onboarding_flow_service.facade.UserFacade;
import com.disqo.onboarding_flow_service.persistance.entity.Mentee;
import com.disqo.onboarding_flow_service.persistance.entity.Mentor;
import com.disqo.onboarding_flow_service.service.MenteeService;
import com.disqo.onboarding_flow_service.service.MentorService;
import com.disqo.onboarding_flow_service.service.dto.MenteeDto;
import com.disqo.onboarding_flow_service.service.dto.MentorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Facade
public class UserFacadeImpl implements UserFacade {

    private final static Logger log = LoggerFactory.getLogger(UserFacadeImpl.class);

    private final JiraIntegrationClientFacade jiraClientFacade;
    private final MenteeService menteeService;
    private final MentorService mentorService;
    private final MenteeConverter menteeConverter;
    private final MentorConverter mentorConverter;
    private final MailSenderClient mailSenderClient;

    public UserFacadeImpl(final JiraIntegrationClientFacade jiraClientFacade,
                          final MenteeService menteeService,
                          final MentorService mentorService,
                          final MenteeConverter menteeConverter,
                          final MentorConverter mentorConverter,
                          final MailSenderClient mailSenderClient) {
        this.jiraClientFacade = jiraClientFacade;
        this.menteeService = menteeService;
        this.mentorService = mentorService;
        this.menteeConverter = menteeConverter;
        this.mentorConverter = mentorConverter;
        this.mailSenderClient = mailSenderClient;
    }

    @Override
    public MenteeDto createMentee(final MenteeDto menteeDto) {
        log.info("Start mentee creating");
        final JiraUserDto jiraUser = this.jiraClientFacade.createUser(
                new JiraUserDto(menteeDto.getEmail(), menteeDto.getDisplayName()));
        final Mentee mentee = this.menteeService.create(menteeDto, jiraUser);
//        MailDTO mailDTO = new MailDTO();
//        mailSenderClient.sendEmail(mailDTO);
        log.info("Finished mentee creating");
        return this.menteeConverter.convertToDto(mentee);
    }

    @Override
    public MenteeDto findMenteeById(final Long id) {
        final Mentee mentee = this.menteeService.findById(id);
        return this.menteeConverter.convertToDto(mentee);
    }

    @Override
    public MentorDto findMentorById(final Long id) {
        final Mentor mentor = this.mentorService.findById(id);
        return this.mentorConverter.convertToDTO(mentor);
    }

    @Override
    public List<MenteeDto> getAllMentees() {
        return this.menteeConverter.bulkConvertToDto(this.menteeService.findALl());
    }

    @Override
    public List<MentorDto> getAllMentors() {
        return this.mentorConverter.bulkConvertToDTO(this.mentorService.findALl());
    }

    @Override
    public boolean deleteMentee(final Long id) {
        return this.menteeService.deleteById(id);
    }

    @Override
    public boolean deleteMentor(final Long id) {
        return this.mentorService.deleteById(id);
    }
}
