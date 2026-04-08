package com.ums.usecases.user.impl;

import com.ums.domain.User;
import com.ums.usecases.ports.UserPersistenceAdapter;
import com.ums.usecases.user.UpdateUserUseCase;
import com.ums.usecases.user.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserPersistenceAdapter userPersistenceAdapter;

    @Override
    public User update(String cin, User u) {
        if(!userPersistenceAdapter.existsById(cin)){
            throw new UserNotFoundException(u.getCin());
        }

        return userPersistenceAdapter.update(cin, u);
    }
}