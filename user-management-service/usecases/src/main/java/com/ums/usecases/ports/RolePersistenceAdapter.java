package com.ums.usecases.ports;

import com.ums.domain.Role;

public interface RolePersistenceAdapter {
    boolean exists(Role r);
    void add(Role r);
    Role update(Role r);
}
