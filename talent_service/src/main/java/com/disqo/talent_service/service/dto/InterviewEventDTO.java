package com.disqo.talent_service.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class InterviewEventDTO implements Serializable {

    @JsonProperty(value = "event")
    @JsonIgnore
    private String eventType;

    @JsonProperty(value = "end_time")
    private LocalDate endTime;

    @JsonProperty(value = "start_time")
    private LocalDate startTime;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty("talent_email")
    private String talentEmail;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(final String eventType) {
        this.eventType = eventType;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(final LocalDate endTime) {
        this.endTime = endTime;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(final LocalDate startTime) {
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getTalentEmail() {
        return talentEmail;
    }

    public void setTalentEmail(final String talentEmail) {
        this.talentEmail = talentEmail;
    }
}
