package com.company.app.model.exception;

public class NoSuchElementException extends RuntimeException {
    public NoSuchElementException(String message) {
        super(message);
    }

    public NoSuchElementException() {
    }
}
