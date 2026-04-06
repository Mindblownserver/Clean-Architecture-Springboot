package com.kharrat.cleanarchitecturetest.core.usecase.role.exceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super("Role "+message+ " not found");
    }
}
