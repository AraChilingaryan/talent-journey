package com.disqo.calendly_service.service;

import com.disqo.calendly_service.service.dto.EventResponse;
import com.disqo.calendly_service.service.dto.WebhookDto;

import java.io.IOException;
import java.util.Map;

public interface CalendarService {

    void sendEventToClient(WebhookDto webhook);

    <T> void post(String uri, T data);

    EventResponse getEvent(final String uri);

}
