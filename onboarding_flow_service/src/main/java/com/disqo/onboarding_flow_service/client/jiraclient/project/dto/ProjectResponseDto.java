package com.disqo.onboarding_flow_service.client.jiraclient.project.dto;

import com.disqo.onboarding_flow_service.client.jiraclient.enums.AssigneeType;
import com.disqo.onboarding_flow_service.client.jiraclient.user.dto.JiraUserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectResponseDto {

    private String self;
    private Long id;
    private String key;
    private String name;
    private String description;
    private JiraUserDto lead;
    private String projectTypeKey;
    private String uuid;
    private AssigneeType assigneeType;
}
