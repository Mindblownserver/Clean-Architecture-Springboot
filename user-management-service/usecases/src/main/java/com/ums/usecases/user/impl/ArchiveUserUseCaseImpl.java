package com.ums.usecases.user.impl;

import com.ums.usecases.ports.UserPersistenceAdapter;
import com.ums.usecases.user.ArchiveUserUseCase;
import com.ums.usecases.user.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArchiveUserUseCaseImpl implements ArchiveUserUseCase {
    private final UserPersistenceAdapter userPersistenceAdapter;

    @Override
    public String archive(String userId) {
        if(!userPersistenceAdapter.existsById(userId)){
            throw new UserNotFoundException(userId);
        }
        userPersistenceAdapter.archive(userId);
        return "User of cin="+userId+" was archived successfully";
    }
}
