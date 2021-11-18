package com.disqo.talent_service.service.utils;

import com.disqo.talent_service.client.dto.MailDTO;
import com.disqo.talent_service.persistance.entity.Talent;

public class MailGenerator {
    public static MailDTO mailGenerator(Talent talent) {
        return MailDTO.builder()
                .emailTo(talent.getEmail())
                .subject("Thank you")
                .text(generateMailText(talent.getName()))
                .build();
    }

    private static String generateMailText(String name) {
        return String.format("Dear %s We have received your application. Thank you", name);
    }
}
