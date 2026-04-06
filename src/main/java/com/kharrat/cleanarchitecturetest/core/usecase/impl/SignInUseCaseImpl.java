package com.kharrat.cleanarchitecturetest.core.usecase.impl;

import com.kharrat.cleanarchitecturetest.core.usecase.SignInUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SignInUseCaseImpl implements SignInUseCase {
    @Override
    public String signin(String cin, String passwd) {
        if(passwd.trim().isEmpty()){
            // Throw error
        }
        if(cin.trim().isEmpty()){
            // Throw error
        }

        return "";
    }
}
