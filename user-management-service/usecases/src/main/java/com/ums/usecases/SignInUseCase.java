package com.ums.usecases;

import com.ums.usecases.modal.AccessRefreshTokens;

public interface SignInUseCase {
    AccessRefreshTokens signin(String cin, String passwd);
}
