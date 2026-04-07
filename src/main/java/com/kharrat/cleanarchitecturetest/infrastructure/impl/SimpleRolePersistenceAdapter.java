package com.kharrat.cleanarchitecturetest.infrastructure.impl;

import com.kharrat.cleanarchitecturetest.infrastructure.mapper.RoleEntityMapper;
import com.kharrat.cleanarchitecturetest.infrastructure.persistence.entities.RoleEntity;
import com.kharrat.cleanarchitecturetest.infrastructure.persistence.repository.RoleRepository;
import com.kharrat.cleanarchitecturetest.core.domain.Role;
import com.kharrat.cleanarchitecturetest.core.usecase.ports.RolePersistenceAdapter;
import com.kharrat.cleanarchitecturetest.core.usecase.role.exceptions.RoleNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleRolePersistenceAdapter implements RolePersistenceAdapter {
    private final RoleRepository roleRepository;
    private final RoleEntityMapper mapper;
    @Override
    public boolean exists(Role r) {
        return roleRepository.existsById(r.getId());
    }

    @Override
    public void add(Role r) {
        roleRepository.save(
            mapper.toEntity(r)
        );
    }


    @Override
    @Transactional
    public Role update(Role r) {
        RoleEntity entity = roleRepository.findById(r.getId())
                .orElseThrow(()-> new RoleNotFoundException("Role "+r.getName()+" not found"));
        entity.setName(r.getName());
        return mapper.toDomain(
            roleRepository.save(entity)
        );
    }
}
