package com.kharrat.cleanarchitecturetest.core.usecase.ports;

import com.kharrat.cleanarchitecturetest.core.domain.Role;

public interface RolePersistencePort {
    boolean exists(Role r);
    void add(Role r);
    void archive(Role r);
    Role update(Role r);
}
