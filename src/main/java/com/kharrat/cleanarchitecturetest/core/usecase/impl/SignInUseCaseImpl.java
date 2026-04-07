package com.kharrat.cleanarchitecturetest.core.usecase.impl;

import com.kharrat.cleanarchitecturetest.core.domain.User;
import com.kharrat.cleanarchitecturetest.core.usecase.SignInUseCase;
import com.kharrat.cleanarchitecturetest.core.usecase.exception.InvalidAuthException;
import com.kharrat.cleanarchitecturetest.core.usecase.modal.AccessRefreshTokens;
import com.kharrat.cleanarchitecturetest.core.usecase.ports.JwtAdapter;
import com.kharrat.cleanarchitecturetest.core.usecase.ports.PasswordEncoderPort;
import com.kharrat.cleanarchitecturetest.core.usecase.ports.UserPersistenceAdapter;
import com.kharrat.cleanarchitecturetest.core.usecase.user.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SignInUseCaseImpl implements SignInUseCase {
    private final PasswordEncoderPort passwordEncoderPort;
    private final UserPersistenceAdapter userPersistenceAdapter;
    private final JwtAdapter jwtAdapter;

    @Override
    public AccessRefreshTokens signin(String cin, String passwd) {
        if(passwd.trim().isEmpty()){
            // Throw error
            throw new InvalidAuthException("Password is empty");
        }
        if(cin.trim().isEmpty() || cin.length() !=8){
            // Throw error
            throw new InvalidAuthException("Cin is invalid");
        }
        User u = userPersistenceAdapter.findByCin(cin)
                .orElseThrow(()-> new UserNotFoundException("User of cin="+cin+" not found"));
        if(!passwordEncoderPort.passwordCmp(passwd, u.getPasswd())){
            throw new InvalidAuthException("Username or/and password invalid");
        }
        // Generate Jwt tokens
        return new AccessRefreshTokens(
                jwtAdapter.generateAccessToken(u),
                jwtAdapter.generateRefreshToken(u)
        );

    }
}
