package com.disqo.onboarding_flow_service.client.sprint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class SprintDto {

    private Long id;
    private String self;
    private String state;
    private String name;
    private String endDate;
    private String startDate;
    private Long originBoardId;

}
