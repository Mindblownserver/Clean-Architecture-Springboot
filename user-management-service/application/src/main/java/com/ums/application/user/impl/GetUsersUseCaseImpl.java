package com.ums.application.user.impl;

import com.ums.domain.User;
import com.ums.application.ports.UserPersistenceAdapter;
import com.ums.application.user.GetUsersUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {
    private final UserPersistenceAdapter userPersistenceAdapter;

    @Override
    public List<User> getUsers() {
        return userPersistenceAdapter.findAll();
    }
}
