package com.disqo.onboarding_flow_service.service.dto;

import com.disqo.onboarding_flow_service.service.enums.RoadmapStatusClientType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RoadmapDTO {

    @JsonIgnore
    private Long id;

    private Date startDate;

    private Date endDate;

    private Long mentorId;

    private Long menteeId;

    private String description;

    @JsonIgnore
    private RoadmapStatusClientType status;

    //TODO do we need sprints here?
}
