package com.ums.usecases.user.impl;

import com.ums.domain.User;
import com.ums.usecases.ports.PasswordEncoderPort;
import com.ums.usecases.ports.UserPersistenceAdapter;
import com.ums.usecases.user.AddUserUseCase;
import com.ums.usecases.user.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddUserUseCaseImpl implements AddUserUseCase {
    private final UserPersistenceAdapter userPersistenceAdapter;
    private final PasswordEncoderPort passwordEncoderPort;
    @Override
    public String add(User u) {
        // Verify if user exists, return exception
        if(userPersistenceAdapter.existsById(u.getCin())){
            throw new UserAlreadyExistsException(u.getCin());
        }
        // hash the password
        u.setPasswd(passwordEncoderPort.encodePassword(u.getPasswd()));

        userPersistenceAdapter.add(u);
        return "User of cin="+u.getCin()+" was added successfully";
    }
}
