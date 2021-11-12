package com.disqo.talent_service.exception;

import com.disqo.talent_service.validation.ValidationError;

public class ViolationException extends RuntimeException{

    private final ValidationError validationError;

    public ViolationException(ValidationError validationError) {
        this.validationError = validationError;
    }

    public ValidationError getValidationError() {
        return validationError;
    }
}
