package com.ums.usecases.user.impl;

import com.ums.domain.User;
import com.ums.usecases.ports.UserPersistenceAdapter;
import com.ums.usecases.user.GetUsersUseCase;
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
