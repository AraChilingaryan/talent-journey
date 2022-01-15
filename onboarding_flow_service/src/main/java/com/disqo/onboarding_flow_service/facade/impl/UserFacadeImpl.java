package com.disqo.onboarding_flow_service.facade.impl;

import com.disqo.onboarding_flow_service.annotation.Facade;
import com.disqo.onboarding_flow_service.client.jiraclient.JiraIntegrationClientFacade;
import com.disqo.onboarding_flow_service.client.jiraclient.user.dto.JiraUserDto;
import com.disqo.onboarding_flow_service.client.mailclient.MailGenerator;
import com.disqo.onboarding_flow_service.client.mailclient.MailSenderClient;
import com.disqo.onboarding_flow_service.converter.MenteeConverter;
import com.disqo.onboarding_flow_service.converter.MentorConverter;
import com.disqo.onboarding_flow_service.facade.UserFacade;
import com.disqo.onboarding_flow_service.persistance.entity.Mentee;
import com.disqo.onboarding_flow_service.persistance.entity.Mentor;
import com.disqo.onboarding_flow_service.service.MenteeService;
import com.disqo.onboarding_flow_service.service.MentorService;
import com.disqo.onboarding_flow_service.service.dto.MenteeDto;
import com.disqo.onboarding_flow_service.service.dto.MentorDto;
import com.disqo.onboarding_flow_service.thymeleafTemplate.ITemplateResolverConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Facade
@RequiredArgsConstructor
@Slf4j
public class UserFacadeImpl implements UserFacade {

    private final JiraIntegrationClientFacade jiraClientFacade;
    private final MenteeService menteeService;
    private final MentorService mentorService;
    private final MenteeConverter menteeConverter;
    private final MentorConverter mentorConverter;
    private final MailSenderClient mailSenderClient;
    private final MailGenerator mailGenerator = new MailGenerator(new ITemplateResolverConfig().thymeleafTemplateEngine());

    @Override
    public MenteeDto createMentee(final MenteeDto menteeDto) {
        log.info("Start mentee creating");
        final JiraUserDto jiraUser = this.jiraClientFacade.createUser(
                new JiraUserDto(menteeDto.getEmail(), menteeDto.getDisplayName()));
        final Mentee mentee = this.menteeService.create(menteeDto, jiraUser);
//        mailSenderClient.sendEmail(mailGenerator.firstMeetingMailGenerator(mentee));
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
