package com.ums.usecases.user.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super("User of cin="+message+" already exists");
    }
}
