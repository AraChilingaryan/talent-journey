package com.disqo.interview_flow_service.client;

import com.disqo.interview_flow_service.persistance.entity.Talent;
import com.disqo.interview_flow_service.persistance.entity.User;
import com.disqo.interview_flow_service.persistance.enums.EmailTextType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class MailTextGenerator {


    private final SpringTemplateEngine thymeleafTemplateEngine;


    private static final String subject = "Interview invitation with Talent_Journey company";

    public static String getSubject() {
        return subject;
    }

    public String getEmailText(Talent talent, User user, EmailTextType emailTextType, URI uri) {
        Context thymeleafContext = new Context();
        switch (emailTextType) {
            case TO_TALENT_FIRST:
                thymeleafContext.setVariable("link", uri);
                thymeleafContext.setVariable("name", talent.getName());
                return thymeleafTemplateEngine.process("mail_to_talent.html", thymeleafContext);

            case TO_TALENT:
                thymeleafContext.setVariable("link", uri);
                thymeleafContext.setVariable("name", talent.getName());
                return thymeleafTemplateEngine.process("mail_to_talent_again.html", thymeleafContext);

            case TO_USER:
                thymeleafContext.setVariable("name", user.getFirstName());
                return thymeleafTemplateEngine.process("mail_to_user.html", thymeleafContext);

            default:
                return "";

        }
    }


}
