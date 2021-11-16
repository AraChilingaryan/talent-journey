package com.disqo.onboarding_flow_service.client.mailclient.dto;

import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.net.URI;

@Builder
public class MailDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String emailTo;

    @NotBlank(message = "subject is required")
    private String subject;

    private String text;

    private URI linkOfCalendar;

    public MailDTO(String emailTo, String subject, String text) {
        this.emailTo = emailTo;
        this.subject = subject;
        this.text = text;
    }

    public MailDTO(String emailTo, String subject, String text, URI linkOfCalendar) {
        this.emailTo = emailTo;
        this.subject = subject;
        this.text = text;
        this.linkOfCalendar = linkOfCalendar;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public URI getLinkOfCalendar() {
        return linkOfCalendar;
    }

    public void setLinkOfCalendar(URI linkOfCalendar) {
        this.linkOfCalendar = linkOfCalendar;
    }
}