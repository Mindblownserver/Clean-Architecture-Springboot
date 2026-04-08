package com.ums.infrastructure.mapper;

import com.ums.domain.User;
import com.ums.infrastructure.persistence.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserEntityMapper {
    @Mapping(target = "passwd", source = "entity.passwdHash")
    User toDomain(UserEntity entity);

    //TODO: Test if this crap works!
    List<User> toDomainList(List<UserEntity> entityList);

    @Mapping(target = "passwdHash", source = "u.passwd")
    UserEntity toEntity(User u);

    default Optional<User> toOptionalDomain(Optional<UserEntity> entity){
        return entity.map(this::toDomain);
    }
}
