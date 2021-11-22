package com.disqo.onboarding_flow_service.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SprintDto {

    private Long id;
    private Long roadMapId;
    private Date startDate;
    private Date endDate;
    private String name;

}
