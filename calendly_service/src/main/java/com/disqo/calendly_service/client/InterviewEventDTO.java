package com.disqo.calendly_service.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
public class InterviewEventDTO implements Serializable {

    @JsonProperty(value = "event")
    private String eventType;

    @JsonProperty(value = "end_time")
    private Date endTime;

    @JsonProperty(value = "start_time")
    private Date startTime;

    @JsonProperty(value = "event_name")
    private String eventName;

    @JsonProperty("talent_email")
    private String talentEmail;

    @JsonProperty(value = "participant_name")
    private String participantName;
}