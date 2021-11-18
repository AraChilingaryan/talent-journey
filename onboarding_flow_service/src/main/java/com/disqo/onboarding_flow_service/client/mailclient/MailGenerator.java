package com.disqo.onboarding_flow_service.client.mailclient;

import com.disqo.onboarding_flow_service.client.mailclient.dto.MailDTO;
import com.disqo.onboarding_flow_service.persistance.entity.Mentee;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.annotation.processing.AbstractProcessor;

@Service
public class MailGenerator {
    public static String sprintCheckupSubject = "Talent Journey: Sprint checkup time";
    public static String firstMeetingSubject = "Talent Journey: Meeting with the CEO";

    private final SpringTemplateEngine thymeleafTemplateEngine;

    public MailGenerator(SpringTemplateEngine thymeleafTemplateEngine) {
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }

    public MailDTO firstMeetingMailGenerator(Mentee mentee) {
        Context thymeleafContext = new Context();
        return MailDTO.builder()
                .emailTo(mentee.getEmail())
                .subject(firstMeetingSubject)
                .text(thymeleafTemplateEngine.process("mail_to_talent.html", thymeleafContext))
                .build();
    }

    public MailDTO sprintCheckupMailGenerator(Mentee mentee) {
        Context thymeleafContext = new Context();
        return MailDTO.builder()
                .emailTo(mentee.getEmail())
                .subject(sprintCheckupSubject)
                .text(thymeleafTemplateEngine.process("mail_to_talent.html", thymeleafContext))
                .build();
    }

    //TODO change the texts
}
