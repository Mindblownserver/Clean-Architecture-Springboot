package com.ums.application.user;

import com.ums.domain.User;

public interface UpdateUserUseCase {
    User update(String cin, User u);
}
