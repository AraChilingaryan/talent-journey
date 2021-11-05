package com.disqo.calendly_service.controller;

import com.disqo.calendly_service.service.CalendarService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@RestController
public class CalendarController {

    @Value("${my.access.token}")
    private String myAccessToken;
    private final static String bearer = "Bearer ";
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
