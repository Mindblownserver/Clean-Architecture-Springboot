package com.ums.application.ports;

import com.ums.domain.Role;

import java.util.List;

public interface RolePersistenceAdapter {
    boolean exists(Role r);
    void add(Role r);
    Role update(Role r);
    List<Role> findAll();
}
