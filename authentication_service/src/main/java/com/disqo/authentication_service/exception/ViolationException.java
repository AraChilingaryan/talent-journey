package com.disqo.authentication_service.exception;

import com.disqo.authentication_service.validation.ValidationError;

public class ViolationException extends RuntimeException {

    private final ValidationError validationError;

    public ViolationException(ValidationError validationError) {
        this.validationError = validationError;
    }

    public ValidationError getValidationError() {
        return validationError;
    }
}
