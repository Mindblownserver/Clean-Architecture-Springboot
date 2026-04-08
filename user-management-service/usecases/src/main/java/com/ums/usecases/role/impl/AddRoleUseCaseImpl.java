package com.ums.usecases.role.impl;

import com.ums.domain.Role;
import com.ums.usecases.ports.RolePersistenceAdapter;
import com.ums.usecases.role.AddRoleUseCase;
import com.ums.usecases.role.exceptions.RoleAlreadyExistsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddRoleUseCaseImpl implements AddRoleUseCase {
    private final RolePersistenceAdapter rolePersistenceAdapter;

    @Override
    public String add(Role role) {
        if(rolePersistenceAdapter.exists(role)){
            throw new RoleAlreadyExistsException(role.getName());
        }
        role.setId(0);
        rolePersistenceAdapter.add(role);
        return "Role "+role.getName()+ " was added successfully";
    }
}
