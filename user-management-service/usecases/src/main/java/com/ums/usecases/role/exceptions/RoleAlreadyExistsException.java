package com.ums.usecases.role.exceptions;

public class RoleAlreadyExistsException  extends RuntimeException {
    public RoleAlreadyExistsException(String message) {
        super("Role "+message+" already exists");
    }
}
