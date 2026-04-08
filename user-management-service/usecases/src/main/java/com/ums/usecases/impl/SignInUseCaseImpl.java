package com.ums.usecases.impl;

import com.ums.domain.User;
import com.ums.usecases.SignInUseCase;
import com.ums.usecases.exception.InvalidAuthException;
import com.ums.usecases.modal.AccessRefreshTokens;
import com.ums.usecases.ports.JwtAdapter;
import com.ums.usecases.ports.PasswordEncoderPort;
import com.ums.usecases.ports.UserPersistenceAdapter;
import com.ums.usecases.user.exceptions.UserNotFoundException;
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
        User u =userPersistenceAdapter.findByCin(cin)
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
