package com.disqo.calendly_service.controller;

import com.disqo.calendly_service.service.CalendarService;
import com.disqo.calendly_service.service.dto.EventResponse;
import com.disqo.calendly_service.service.dto.WebhookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class CalendarController {

    private final static String URI_CALENDLY = "https://api.calendly.com";
    private final static String SCHEDULE_EVENT = "/scheduled_events/";

    private final CalendarService calendarService;

    public CalendarController(final CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/getPayload")
    public void getMyPayload(@RequestBody WebhookDto webhook) {
        this.calendarService.sendEventToClient(webhook);
    }

    @GetMapping("/getEventByUuid/{uuid}")
    public ResponseEntity<EventResponse> getInterviewDetails(@PathVariable String uuid) {
        EventResponse response = this.calendarService.getEvent(URI_CALENDLY + SCHEDULE_EVENT + uuid);
        return ResponseEntity.ok(response);
    }
}
