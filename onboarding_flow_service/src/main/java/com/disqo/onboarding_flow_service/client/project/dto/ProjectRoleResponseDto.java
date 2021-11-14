package com.disqo.onboarding_flow_service.client.project.dto;

import com.disqo.onboarding_flow_service.client.user.dto.JiraUserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectRoleResponseDto {

    private String self;
    private String name;
    private String description;
    private List<JiraUserDto> actors;


}
