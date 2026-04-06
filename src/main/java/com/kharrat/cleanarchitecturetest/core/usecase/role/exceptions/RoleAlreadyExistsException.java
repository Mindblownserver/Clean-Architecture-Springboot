package com.kharrat.cleanarchitecturetest.core.usecase.role.exceptions;

public class RoleAlreadyExistsException  extends RuntimeException {
    public RoleAlreadyExistsException(String message) {
        super("Role "+message+" already exists");
    }
}
