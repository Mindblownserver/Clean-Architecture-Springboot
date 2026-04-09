package com.ums.infrastructure.impl.user;

import com.ums.domain.User;
import com.ums.application.ports.PasswordEncoderPort;
import com.ums.application.ports.UserPersistenceAdapter;
import com.ums.application.user.AddUserUseCase;
import com.ums.application.user.exceptions.UserAlreadyExistsException;
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
