package com.disqo.onboarding_flow_service.client.mailclient;

import com.disqo.onboarding_flow_service.client.mailclient.dto.MailDTO;

public interface MailSenderClient {
    void sendEmail(MailDTO mailDTO);
}
