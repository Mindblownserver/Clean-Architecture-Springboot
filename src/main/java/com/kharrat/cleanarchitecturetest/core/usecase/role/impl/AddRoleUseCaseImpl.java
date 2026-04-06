package com.kharrat.cleanarchitecturetest.core.usecase.role.impl;

import com.kharrat.cleanarchitecturetest.core.domain.Role;
import com.kharrat.cleanarchitecturetest.core.usecase.role.AddRoleUseCase;
import com.kharrat.cleanarchitecturetest.core.usecase.ports.RolePersistencePort;
import com.kharrat.cleanarchitecturetest.core.usecase.role.exceptions.RoleAlreadyExistsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddRoleUseCaseImpl implements AddRoleUseCase {
    private final RolePersistencePort rolePersistencePort;

    @Override
    public String add(Role role) {
        if(rolePersistencePort.exists(role)){
            throw new RoleAlreadyExistsException(role.getName());
        }
        rolePersistencePort.add(role);
        return "Role "+role.getName()+ " was added successfully";
    }
}
