package com.ums.application.role.impl;

import com.ums.domain.Role;
import com.ums.application.ports.RolePersistenceAdapter;
import com.ums.application.role.UpdateRoleUseCase;
import com.ums.application.role.exceptions.RoleNotFoundException;
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
