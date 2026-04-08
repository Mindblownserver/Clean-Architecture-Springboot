package com.ums.application.ports;


import com.ums.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserPersistenceAdapter {
    boolean existsById(String userId);
    void add(User u);
    void archive(String userId);
    User update(String cin, User u);
    Optional<User> findByCin(String cin);
    List<User> findAll();
}
