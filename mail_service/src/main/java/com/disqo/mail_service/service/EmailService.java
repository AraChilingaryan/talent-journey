package com.disqo.mail_service.service;

import com.disqo.mail_service.service.model.Mail;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

public interface EmailService {

    void sendEmail(Mail mail);

    void sendMailWithAttachments(Mail Mail, MultipartFile multipartFile) throws MessagingException;
}
