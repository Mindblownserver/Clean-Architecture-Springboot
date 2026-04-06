package com.kharrat.cleanarchitecturetest.core.usecase.user.impl;

import com.kharrat.cleanarchitecturetest.core.domain.User;
import com.kharrat.cleanarchitecturetest.core.usecase.user.ArchiveUserUseCase;
import com.kharrat.cleanarchitecturetest.core.usecase.ports.UserPersistenceAdapter;
import com.kharrat.cleanarchitecturetest.core.usecase.user.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArchiveUserUseCaseImpl implements ArchiveUserUseCase {
    private final UserPersistenceAdapter userPersistenceAdapter;

    @Override
    public String archive(User u) {
        if(!userPersistenceAdapter.exists(u)){
            throw new UserNotFoundException(u.getCin());
        }
        userPersistenceAdapter.archive(u);
        return "User of cin="+u+" was archived successfully";
    }
}
