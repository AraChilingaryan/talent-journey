package com.disqo.mail_service.service;

import com.disqo.mail_service.service.model.Mail;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {

    void sendEmail(Mail mail);

    void sendEmailHtml(Mail mail);

    void sendMailWithAttachments(Mail Mail, MultipartFile multipartFile);
}
