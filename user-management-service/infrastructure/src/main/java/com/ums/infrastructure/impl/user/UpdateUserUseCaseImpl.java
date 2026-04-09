package com.ums.infrastructure.impl.user;

import com.ums.domain.User;
import com.ums.application.ports.UserPersistenceAdapter;
import com.ums.application.user.UpdateUserUseCase;
import com.ums.application.user.exceptions.UserNotFoundException;
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