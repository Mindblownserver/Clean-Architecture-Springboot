package com.ums.application.config;

import com.ums.infrastructure.impl.SimpleRolePersistenceAdapter;
import com.ums.infrastructure.mapper.RoleEntityMapper;
import com.ums.infrastructure.persistence.repository.RoleRepository;
import com.ums.usecases.ports.RolePersistenceAdapter;
import com.ums.usecases.role.AddRoleUseCase;
import com.ums.usecases.role.UpdateRoleUseCase;
import com.ums.usecases.role.impl.AddRoleUseCaseImpl;
import com.ums.usecases.role.impl.UpdateRoleUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleUseCaseConfig {
    @Bean
    public RolePersistenceAdapter rolePersistenceAdapter(RoleRepository roleRepository, RoleEntityMapper mapper){
        return new SimpleRolePersistenceAdapter(
                roleRepository,
                mapper
        );
    }

    @Bean
    public AddRoleUseCase addRoleUseCase(RolePersistenceAdapter rolePersistenceAdapter){
        return new AddRoleUseCaseImpl(rolePersistenceAdapter);
    }

    @Bean
    public UpdateRoleUseCase updateRoleUseCase(RolePersistenceAdapter rolePersistenceAdapter){
        return new UpdateRoleUseCaseImpl(rolePersistenceAdapter);
    }
}
