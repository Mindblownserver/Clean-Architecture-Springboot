package com.ums.infrastructure.impl.role;

import com.ums.domain.Role;
import com.ums.application.ports.RolePersistenceAdapter;
import com.ums.application.role.AddRoleUseCase;
import com.ums.application.role.exceptions.RoleAlreadyExistsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddRoleUseCaseImpl implements AddRoleUseCase {
    private final RolePersistenceAdapter rolePersistenceAdapter;

    @Override
    public String add(Role role) throws RoleAlreadyExistsException {
        if(rolePersistenceAdapter.exists(role)){
            throw new RoleAlreadyExistsException(role.getName());
        }
        role.setId(0);
        rolePersistenceAdapter.add(role);
        return "Role "+role.getName()+ " was added successfully";
    }
}
