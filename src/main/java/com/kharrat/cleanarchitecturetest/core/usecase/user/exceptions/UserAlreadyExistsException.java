package com.kharrat.cleanarchitecturetest.core.usecase.user.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super("User of cin="+message+" already exists");
    }
}
