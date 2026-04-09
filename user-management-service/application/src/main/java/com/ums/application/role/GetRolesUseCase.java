package com.ums.application.role;

import com.ums.application.role.exceptions.RoleNotFoundException;
import com.ums.domain.Role;

import java.util.List;

public interface GetRolesUseCase {
    List<Role> getRoles() throws RoleNotFoundException;
}
