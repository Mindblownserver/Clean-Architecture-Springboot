package com.ums.usecases.user;

import com.ums.domain.User;

public interface UpdateUserUseCase {
    User update(String cin, User u);
}
