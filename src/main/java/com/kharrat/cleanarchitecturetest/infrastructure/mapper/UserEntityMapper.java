package com.kharrat.cleanarchitecturetest.infrastructure.mapper;

import com.kharrat.cleanarchitecturetest.infrastructure.persistence.entities.UserEntity;
import com.kharrat.cleanarchitecturetest.core.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    @Mapping(target = "passwd", source = "entity.passwdHash")
    User toDomain(UserEntity entity);

    @Mapping(target = "passwdHash", source = "u.passwd")
    UserEntity toEntity(User u);

    default Optional<User> toOptionalDomain(Optional<UserEntity> entity){
        return entity.map(this::toDomain);
    }
}
