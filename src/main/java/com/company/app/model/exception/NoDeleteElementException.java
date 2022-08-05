package com.company.app.model.exception;

public class NoDeleteElementException extends RuntimeException {

    public NoDeleteElementException(String message) {
        super(message);
    }

    public NoDeleteElementException() {
    }
}
