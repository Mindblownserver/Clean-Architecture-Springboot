package com.kharrat.cleanarchitecturetest.infrastructure.mapper;

import com.kharrat.cleanarchitecturetest.infrastructure.persistence.entities.RoleEntity;
import com.kharrat.cleanarchitecturetest.core.domain.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleEntityMapper {
    Role toDomain(RoleEntity entity);
    RoleEntity toEntity(Role r);
}
