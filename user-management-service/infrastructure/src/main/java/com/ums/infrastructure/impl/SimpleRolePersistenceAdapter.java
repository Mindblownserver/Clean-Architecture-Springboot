package com.ums.infrastructure.impl;

import com.ums.domain.Role;
import com.ums.infrastructure.mapper.RoleEntityMapper;
import com.ums.infrastructure.persistence.entities.RoleEntity;
import com.ums.infrastructure.persistence.repository.RoleRepository;
import com.ums.application.ports.RolePersistenceAdapter;
import com.ums.application.role.exceptions.RoleNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    @Override
    public List<Role> findAll() {
        return mapper.toDomainList(
                roleRepository.findAll()
        );
    }
}
