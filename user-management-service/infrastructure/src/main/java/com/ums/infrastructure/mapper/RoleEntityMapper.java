package com.ums.infrastructure.mapper;

import com.ums.domain.Role;
import com.ums.infrastructure.persistence.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleEntityMapper {
    Role toDomain(RoleEntity entity);
    RoleEntity toEntity(Role r);
}
