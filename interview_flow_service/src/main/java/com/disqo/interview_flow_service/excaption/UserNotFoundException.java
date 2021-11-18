package com.disqo.interview_flow_service.excaption;

import lombok.Getter;

public class UserNotFoundException extends RuntimeException {
    @Getter
    private final String message;

    @Getter
    private final Object data;

    public UserNotFoundException(String message, Object data) {
        super(message);
        this.message = message;
        this.data = data;
    }
}
