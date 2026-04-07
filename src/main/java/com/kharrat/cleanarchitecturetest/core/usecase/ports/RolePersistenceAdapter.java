package com.kharrat.cleanarchitecturetest.core.usecase.ports;

import com.kharrat.cleanarchitecturetest.core.domain.Role;

public interface RolePersistenceAdapter {
    boolean exists(Role r);
    void add(Role r);
    Role update(Role r);
}
