package com.disqo.onboarding_flow_service.client.jiraclient.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectBoardDto {

    private int total;
    private LinkedList<BoardDto> values;

}
