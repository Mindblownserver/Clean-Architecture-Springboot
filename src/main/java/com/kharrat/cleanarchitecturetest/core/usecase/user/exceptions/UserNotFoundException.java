package com.kharrat.cleanarchitecturetest.core.usecase.user.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("User of cin="+message+" doesn't exist");
    }
}
