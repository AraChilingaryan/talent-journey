package com.disqo.mail_service.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.net.URI;

public class Mail {

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String emailTo;

    @NotBlank(message = "subject is required")
    private String subject;

    private String text;

    @JsonIgnore
    private URI linkOfCalendar;

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(final String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public URI getLinkOfCalendar() {
        return linkOfCalendar;
    }

    public void setLinkOfCalendar(final URI linkOfCalendar) {
        this.linkOfCalendar = linkOfCalendar;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }
}
