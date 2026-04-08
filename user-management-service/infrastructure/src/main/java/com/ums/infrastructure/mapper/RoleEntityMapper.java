package com.ums.infrastructure.mapper;

import com.ums.domain.Role;
import com.ums.infrastructure.persistence.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleEntityMapper {
    Role toDomain(RoleEntity entity);

    List<Role> toDomainList(List<RoleEntity> entityList);

    RoleEntity toEntity(Role r);
}
