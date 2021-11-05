package com.disqo.calendly_service.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;

public class InterviewEventDTO implements Serializable {

    @JsonProperty(value = "event")
    private String eventType;

    @JsonIgnore
    @JsonProperty(value = "end_time")
    private String endTime;

    @JsonIgnore
    @JsonProperty(value = "start_time")
    private String startTime;

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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(final String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(final String startTime) {
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

//    {
//            "resource":{
//            "created_at":"2021-11-02T09:02:41.344668Z",
//            "end_time":"2021-11-03T06:45:00.000000Z",
//            "event_guests":[],
//            "event_memberships":[
//            {
//            "user":"https://api.calendly.com/users/2e38acad-b10c-4a10-b178-128ef6ef1e27"
//            }
//            ],
//            "event_type":"https://api.calendly.com/event_types/b7b85232-6b95-4d6c-8d5c-d8dd4825c7a8",
//            "invitees_counter":{
//            "active":1,
//            "limit":1,
//            "total":1
//            },
//            "location":{
//            "location":null,
//            "type":"custom"
//            },
//            "name":"on to one",
//            "start_time":"2021-11-03T06:30:00.000000Z",
//            "status":"active",
//            "updated_at":"2021-11-02T09:02:41.935143Z",
//            "uri":"https://api.calendly.com/scheduled_events/7c8d5a03-6b1f-426f-9a05-b1fd95527aa3"
//            }
//            }
