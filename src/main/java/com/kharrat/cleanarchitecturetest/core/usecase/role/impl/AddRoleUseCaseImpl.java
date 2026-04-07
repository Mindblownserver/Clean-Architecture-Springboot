package com.kharrat.cleanarchitecturetest.core.usecase.role.impl;

import com.kharrat.cleanarchitecturetest.core.domain.Role;
import com.kharrat.cleanarchitecturetest.core.usecase.role.AddRoleUseCase;
import com.kharrat.cleanarchitecturetest.core.usecase.ports.RolePersistenceAdapter;
import com.kharrat.cleanarchitecturetest.core.usecase.role.exceptions.RoleAlreadyExistsException;
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
