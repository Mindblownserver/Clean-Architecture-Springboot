package com.ums.application.ports;

public interface PasswordEncoderPort {
    String encodePassword(String passwordInClear);
    boolean passwordCmp(String passwordInClear, String passwordHash);
}
