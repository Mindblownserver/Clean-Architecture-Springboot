package com.kharrat.cleanarchitecturetest.core.usecase.ports;

public interface PasswordEncoderPort {
    String encodePassword(String passwordInClear);
    boolean passwordCmp(String passwordInClear, String passwordHash);
}
