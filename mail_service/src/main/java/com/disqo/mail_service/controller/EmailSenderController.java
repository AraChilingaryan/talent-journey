package com.disqo.mail_service.controller;

import com.disqo.mail_service.service.EmailService;
import com.disqo.mail_service.service.model.Mail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

@RestController("/sendEmail")
public class EmailSenderController {

    private final EmailService emailService;

    public EmailSenderController(final EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMail(@RequestBody Mail mail) {
        emailService.sendEmail(mail);
        return new ResponseEntity<>("Email Sent successfully", HttpStatus.OK);
    }

    @PostMapping("/attachment")
    public ResponseEntity<String> sendAttachmentEmail(@RequestBody Mail mail,@RequestParam("file") MultipartFile multipartFile) throws MessagingException {
        emailService.sendMailWithAttachments(mail, multipartFile);
        return new ResponseEntity<>("Attachment mail sent successfully", HttpStatus.OK);
    }
}
