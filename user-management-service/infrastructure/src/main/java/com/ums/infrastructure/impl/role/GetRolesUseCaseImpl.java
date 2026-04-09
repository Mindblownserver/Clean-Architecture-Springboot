package com.ums.infrastructure.impl.role;

import com.ums.application.role.exceptions.RoleNotFoundException;
import com.ums.domain.Role;
import com.ums.application.ports.RolePersistenceAdapter;
import com.ums.application.role.GetRolesUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetRolesUseCaseImpl implements GetRolesUseCase {
    private final RolePersistenceAdapter rolePersistenceAdapter;

    @Override
    public List<Role> getRoles() throws RoleNotFoundException {
        return rolePersistenceAdapter.findAll();
    }
}
