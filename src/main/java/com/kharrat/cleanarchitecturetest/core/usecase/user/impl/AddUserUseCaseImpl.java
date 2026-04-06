package com.kharrat.cleanarchitecturetest.core.usecase.user.impl;

import com.kharrat.cleanarchitecturetest.core.domain.User;
import com.kharrat.cleanarchitecturetest.core.usecase.user.AddUserUseCase;
import com.kharrat.cleanarchitecturetest.core.usecase.ports.UserPersistenceAdapter;
import com.kharrat.cleanarchitecturetest.core.usecase.user.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddUserUseCaseImpl implements AddUserUseCase {
    private final UserPersistenceAdapter userPersistenceAdapter;

    @Override
    public String add(User u) {
        // Verify if user exists, return exception
        if(userPersistenceAdapter.exists(u)){
            throw new UserAlreadyExistsException(u.getCin());
        }
        // If not, add it
        userPersistenceAdapter.add(u);
        return "User of cin="+u.getCin()+" was added successfully";
    }
}
