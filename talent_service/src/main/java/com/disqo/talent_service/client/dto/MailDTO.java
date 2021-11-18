package com.disqo.talent_service.client.dto;


import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder
public class MailDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String emailTo;

    @NotBlank(message = "subject is required")
    private String subject;

    private String text;

    public MailDTO(String emailTo, String subject, String text) {
        this.emailTo = emailTo;
        this.subject = subject;
        this.text = text;
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
}
