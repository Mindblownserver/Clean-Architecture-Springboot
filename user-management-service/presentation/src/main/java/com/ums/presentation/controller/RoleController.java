package com.ums.presentation.controller;

import com.ums.domain.Role;
import com.ums.application.role.AddRoleUseCase;
import com.ums.application.role.GetRolesUseCase;
import com.ums.application.role.UpdateRoleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final AddRoleUseCase addRoleUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;
    private final GetRolesUseCase getRolesUseCase;
    @GetMapping
    public List<Role> getAllRoles(){
        return getRolesUseCase.getRoles();
    }
}
