package com.disqo.onboarding_flow_service.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenteeDTO extends UserDTO{

    private Long mentorId;
}
