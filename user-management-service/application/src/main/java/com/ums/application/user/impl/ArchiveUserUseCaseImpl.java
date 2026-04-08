package com.ums.application.user.impl;

import com.ums.application.ports.UserPersistenceAdapter;
import com.ums.application.user.ArchiveUserUseCase;
import com.ums.application.user.exceptions.UserNotFoundException;
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
