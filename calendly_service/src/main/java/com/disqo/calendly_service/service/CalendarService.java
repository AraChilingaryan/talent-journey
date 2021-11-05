package com.disqo.calendly_service.service;

import java.io.IOException;
import java.util.Map;

public interface CalendarService {

    void sendEventToClient(Map<String, Object> webhookResponse) throws IOException, InterruptedException;

    <T> void post(String uri, T data);

    String getRequestToCalendar(String url) throws IOException, InterruptedException;
}
