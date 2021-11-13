package com.disqo.onboarding_flow_service.client.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JiraUserDto {
    private String self;
    private String id;
    private String key;
    private String accountId;
    private String emailAddress;
    private String displayName;

}
