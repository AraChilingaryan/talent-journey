package com.disqo.calendly_service.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

public class PayloadDTO {

    @JsonProperty(value = "created_at")
    private Date createdAt;

    @JsonProperty(value = "event")
    private String eventType;

    @JsonProperty(value = "payload")
    private Map<String, Map<String,Object>> payload;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(final String eventType) {
        this.eventType = eventType;
    }

    public Map<String, Map<String, Object>> getPayload() {
        return payload;
    }

    public void setPayload(final Map<String, Map<String, Object>> payload) {
        this.payload = payload;
    }
}
