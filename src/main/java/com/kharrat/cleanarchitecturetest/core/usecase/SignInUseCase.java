package com.kharrat.cleanarchitecturetest.core.usecase;

import com.kharrat.cleanarchitecturetest.core.usecase.modal.AccessRefreshTokens;

public interface SignInUseCase {
    AccessRefreshTokens signin(String cin, String passwd);
}
