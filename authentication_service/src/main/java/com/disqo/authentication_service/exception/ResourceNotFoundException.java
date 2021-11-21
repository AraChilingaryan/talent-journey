package com.disqo.authentication_service.exception;

import lombok.Getter;

public class ResourceNotFoundException extends RuntimeException{
    @Getter
    private final String message;

    @Getter
    private final Object data;

    public ResourceNotFoundException(String message, Object data) {
        super(message);
        this.message = message;
        this.data = data;
    }
}
