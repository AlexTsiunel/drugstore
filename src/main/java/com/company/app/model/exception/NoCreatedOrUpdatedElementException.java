package com.company.app.model.exception;

public class NoCreatedOrUpdatedElementException extends RuntimeException {
    public NoCreatedOrUpdatedElementException(String message) {
        super(message);
    }

    public NoCreatedOrUpdatedElementException() {
    }
}
