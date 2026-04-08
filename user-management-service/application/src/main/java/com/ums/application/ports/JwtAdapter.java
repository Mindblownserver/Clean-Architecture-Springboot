package com.ums.application.ports;

import com.ums.domain.User;

public interface JwtAdapter {
    String generateAccessToken(User u);
    String generateRefreshToken(User u);
}
