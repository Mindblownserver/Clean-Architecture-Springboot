package com.ums.usecases.ports;

public interface PasswordEncoderPort {
    String encodePassword(String passwordInClear);
    boolean passwordCmp(String passwordInClear, String passwordHash);
}
