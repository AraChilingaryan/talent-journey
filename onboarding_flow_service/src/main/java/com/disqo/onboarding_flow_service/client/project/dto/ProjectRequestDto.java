package com.disqo.onboarding_flow_service.client.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProjectRequestDto {

    @JsonProperty(value = "name")
    private String projectName;
    private String key;
    private String projectTemplateKey;
    private String description;
    private String leadAccountId;
    private AssigneeType assigneeType;

}