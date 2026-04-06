package com.kharrat.cleanarchitecturetest.core.usecase.user.impl;

import com.kharrat.cleanarchitecturetest.core.domain.User;
import com.kharrat.cleanarchitecturetest.core.usecase.user.UpdateUserUseCase;
import com.kharrat.cleanarchitecturetest.core.usecase.ports.UserPersistenceAdapter;
import com.kharrat.cleanarchitecturetest.core.usecase.user.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserPersistenceAdapter userPersistenceAdapter;

    @Override
    public User update(User u) {
        if(!userPersistenceAdapter.exists(u)){
            throw new UserNotFoundException(u.getCin());
        }

        return userPersistenceAdapter.update(u);
    }
}