package com.disqo.calendly_service.client;

import java.util.Map;

public interface InterviewClient {

    InterviewEventDTO generateEventDTOFrom(Map<String, Object> httpEventBody, Map<String, String> payloadDetails);

    void postInterviewEventDTO(InterviewEventDTO interviewEventDTO);
}
