package com.disqo.calendly_service.controller;

import com.disqo.calendly_service.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class CalendarController {

    private final static String URI_CALENDLY = "https://api.calendly.com";
    private final static String SCHEDULE_EVENT = "/scheduled_events/";

    private final CalendarService calendarService;

    public CalendarController(final CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping(value = "/users/me")
    public ResponseEntity<String> getMe() throws IOException, InterruptedException {
        String response = this.calendarService.getRequestToCalendar(URI_CALENDLY + "/users/me");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getPayload")
    public void getMyPayload(@RequestBody Map<String, Object> payload) throws IOException, InterruptedException {
        this.calendarService.sendEventToClient(payload);
    }

    @GetMapping("/getEventByUuid/{uuid}")
    public ResponseEntity<String> getInterviewDetails(@PathVariable String uuid) throws IOException, InterruptedException {
        String response = this.calendarService.getRequestToCalendar(URI_CALENDLY + SCHEDULE_EVENT + uuid);
        return ResponseEntity.ok(response);
    }
}
