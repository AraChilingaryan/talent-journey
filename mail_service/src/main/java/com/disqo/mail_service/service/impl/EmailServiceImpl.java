package com.disqo.mail_service.service.impl;

import com.disqo.mail_service.service.EmailService;
import com.disqo.mail_service.service.model.Mail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${email.from.address}")
    private String setFrom;

    public EmailServiceImpl(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(final Mail mail) {
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(setFrom);
        simpleMailMessage.setTo(mail.getEmailTo());
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mail.getText());
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendMailWithAttachments(final Mail mail, MultipartFile multipart) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(mail.getEmailTo());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getLinkOfCalendar().toString(), true);
        helper.addAttachment(multipart.getName(), multipart);

        javaMailSender.send(msg);
    }
}
