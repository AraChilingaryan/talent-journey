package com.disqo.onboarding_flow_service.exception;

public class MenteeNotFoundException extends RuntimeException {
    private final String message;

    private final Object data;

    public MenteeNotFoundException(String message, Object data) {
        super(message);
        this.message = message;
        this.data = data;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
