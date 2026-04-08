package com.ums.usecases.role.impl;

import com.ums.domain.Role;
import com.ums.usecases.ports.RolePersistenceAdapter;
import com.ums.usecases.role.UpdateRoleUseCase;
import com.ums.usecases.role.exceptions.RoleNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateRoleUseCaseImpl implements UpdateRoleUseCase {
    private final RolePersistenceAdapter rolePersistenceAdapter;
    @Override
    public Role update(Role r) {
        if(!rolePersistenceAdapter.exists(r)){
            throw new RoleNotFoundException(r.getName());
        }

        return rolePersistenceAdapter.update(r);
    }
}
