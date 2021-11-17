package com.disqo.mail_service.controller;

import com.disqo.mail_service.service.EmailService;
import com.disqo.mail_service.service.model.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/sendEmail")
public class EmailSenderController {
    private final static Logger log = LoggerFactory.getLogger(EmailSenderController.class);

    private final EmailService emailService;

    public EmailSenderController(final EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping()
    public ResponseEntity<String> sendEmail(@RequestBody Mail mail) {
        log.info("In EmailSenderController, /sendEmail , endpoint");
        emailService.sendEmail(mail);
        return new ResponseEntity<>("Email Sent successfully", HttpStatus.OK);
    }

    @PostMapping("/html")
    public ResponseEntity<String> sendEmailHtml(@RequestBody Mail mail) {
        log.info("In EmailSenderController, /sendEmail , endpoint");
        emailService.sendEmailHtml(mail);
        return new ResponseEntity<>("Email Sent successfully", HttpStatus.OK);
    }

    @PostMapping("/attachment")
    public ResponseEntity<String> sendAttachmentEmail(@RequestBody Mail mail, @RequestParam("file") MultipartFile multipartFile) {
        log.info("In EmailSenderController, /sendEmail/attachment , endpoint");
        emailService.sendMailWithAttachments(mail, multipartFile);
        return new ResponseEntity<>("Email with attachment sent successfully", HttpStatus.OK);
    }
}
