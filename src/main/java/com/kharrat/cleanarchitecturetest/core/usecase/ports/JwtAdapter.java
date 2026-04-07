package com.kharrat.cleanarchitecturetest.core.usecase.ports;

import com.kharrat.cleanarchitecturetest.core.domain.User;

public interface JwtAdapter {
    String generateAccessToken(User u);
    String generateRefreshToken(User u);
}
