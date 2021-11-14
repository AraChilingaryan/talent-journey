package com.disqo.mail_service.service.impl;

import com.disqo.mail_service.service.EmailService;
import com.disqo.mail_service.service.model.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender javaMailSender;

    @Value("${email.from.address}")
    private String setFrom;

    public EmailServiceImpl(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(final Mail mail) {
        log.info("Started sendEmail");
        final SimpleMailMessage simpleMailMessage = buildMailMessage(mail);
        javaMailSender.send(simpleMailMessage);
        log.info("Finished sendEmail");
    }

    @Override
    public void sendMailWithAttachments(final Mail mail, MultipartFile multipart) {
        log.info("In sendMailWithAttachment");
        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(mail.getEmailTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getLinkOfCalendar().toString(), true);
            helper.addAttachment(multipart.getName(), multipart);
            log.debug("Added a file attachment: {}", multipart.getName());
        } catch (MessagingException e) {
            log.error("Error when trying to attach file", e);
            throw new RuntimeException(e.getMessage());
        }
        javaMailSender.send(msg);
    }

    private SimpleMailMessage buildMailMessage(final Mail mail) {
        log.info("Started creating mail message");
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(setFrom);
        simpleMailMessage.setTo(mail.getEmailTo());
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mail.getText());
        log.info("Finished creating mail message");
        return simpleMailMessage;
    }
}
