package com.disqo.onboarding_flow_service.exception;

import com.disqo.onboarding_flow_service.validation.ValidationError;

public class ViolationException extends RuntimeException{

    private final ValidationError validationError;

    public ViolationException(ValidationError validationError) {
        this.validationError = validationError;
    }

    public ValidationError getValidationError() {
        return validationError;
    }
}
