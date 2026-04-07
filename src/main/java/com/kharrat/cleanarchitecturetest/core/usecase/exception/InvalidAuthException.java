package com.kharrat.cleanarchitecturetest.core.usecase.exception;

public class InvalidAuthException extends RuntimeException {
    public InvalidAuthException(String message) {
        super(message);
    }
}
