package com.disqo.calendly_service.client;

import com.disqo.calendly_service.service.dto.EventDto;
import com.disqo.calendly_service.service.dto.WebhookDto;

public interface InterviewClient {

    InterviewEventDTO generateInterviewEvent(WebhookDto webhook, EventDto event);

    void postInterviewEventDTO(InterviewEventDTO interviewEventDTO);
}
