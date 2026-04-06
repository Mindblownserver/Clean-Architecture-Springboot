package com.kharrat.cleanarchitecturetest.core.usecase.role.impl;

import com.kharrat.cleanarchitecturetest.core.domain.Role;
import com.kharrat.cleanarchitecturetest.core.usecase.ports.RolePersistencePort;
import com.kharrat.cleanarchitecturetest.core.usecase.role.UpdateRoleUseCase;
import com.kharrat.cleanarchitecturetest.core.usecase.role.exceptions.RoleNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateRoleUseCaseImpl implements UpdateRoleUseCase {
    private final RolePersistencePort rolePersistencePort;
    @Override
    public Role update(Role r) {
        if(!rolePersistencePort.exists(r)){
            throw new RoleNotFoundException(r.getName());
        }

        return rolePersistencePort.update(r);
    }
}
