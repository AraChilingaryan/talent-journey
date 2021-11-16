package com.disqo.onboarding_flow_service.client.mailclient;

import com.disqo.onboarding_flow_service.client.mailclient.dto.MailDTO;
import com.disqo.onboarding_flow_service.persistance.entity.Mentee;

public class MailGenerator {
    public static MailDTO firstMeetingMailGenerator(Mentee mentee) {
        return MailDTO.builder()
                .emailTo(mentee.getEmail())
                .subject("Thank you")
                .text(generateMailText(mentee.getFirstName()))
                .build();
    }

    private static String generateMailText(String name) {
        return String.format("Dear %s We have received your application. Thank you", name);
    }

    //TODO change the texts
}
