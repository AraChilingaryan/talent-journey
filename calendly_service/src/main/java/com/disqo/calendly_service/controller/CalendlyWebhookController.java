package com.disqo.calendly_service.controller;

import com.disqo.calendly_service.service.CalendarService;
import com.disqo.calendly_service.service.dto.EventResponse;
import com.disqo.calendly_service.service.dto.WebhookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class CalendlyWebhookController {

    private final CalendarService calendarService;

    public CalendlyWebhookController(final CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/getPayload")
    public void getMyPayload(@RequestBody WebhookDto webhook) {
        this.calendarService.sendEventToClient(webhook);
    }
}
