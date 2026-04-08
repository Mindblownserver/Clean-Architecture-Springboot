package com.ums.application.auth;

import com.ums.application.modal.AccessRefreshTokens;

public interface SignInUseCase {
    AccessRefreshTokens signin(String cin, String passwd);
}
