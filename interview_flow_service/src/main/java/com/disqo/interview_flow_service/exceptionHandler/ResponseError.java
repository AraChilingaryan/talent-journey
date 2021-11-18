package com.disqo.interview_flow_service.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder()
public class ResponseError {
    private int code;
    private String message;
    private Object data;
}
