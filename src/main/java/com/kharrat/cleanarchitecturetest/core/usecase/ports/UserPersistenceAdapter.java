package com.kharrat.cleanarchitecturetest.core.usecase.ports;

import com.kharrat.cleanarchitecturetest.core.domain.User;

import java.util.Optional;

public interface UserPersistenceAdapter {
    boolean exists(User user);
    void add(User u);
    void archive(User u);
    User update(User u);
    Optional<User> findByCin(String cin);
}
