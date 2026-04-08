package com.ums.application.user.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("User of cin="+message+" doesn't exist");
    }
}
