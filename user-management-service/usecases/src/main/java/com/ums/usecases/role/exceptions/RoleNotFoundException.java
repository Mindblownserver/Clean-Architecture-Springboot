package com.ums.usecases.role.exceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super("Role "+message+ " not found");
    }
}
