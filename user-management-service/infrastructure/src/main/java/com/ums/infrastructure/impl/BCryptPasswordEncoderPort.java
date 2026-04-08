package com.ums.infrastructure.impl;


import com.ums.application.ports.PasswordEncoderPort;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

@RequiredArgsConstructor
public class BCryptPasswordEncoderPort implements PasswordEncoderPort {
    private final int logRounds;
    public BCryptPasswordEncoderPort(){
        logRounds= 10;
    }

    @Override
    public String encodePassword(String passwordInClear) {
        String salt = BCrypt.gensalt(logRounds);
        return BCrypt.hashpw(passwordInClear, salt);
    }

    @Override
    public boolean passwordCmp(String passwordInClear, String passwordHash) {
        return BCrypt.checkpw(passwordInClear, passwordHash);
    }
}
