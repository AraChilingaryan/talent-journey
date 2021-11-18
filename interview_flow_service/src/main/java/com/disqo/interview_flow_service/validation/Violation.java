package com.disqo.interview_flow_service.validation;

import lombok.Data;

@Data
public class Violation {

    private final String field;

    private final String message;

}