package com.ums.presentation.mapper;

import com.ums.presentation.dto.AddUserRequestDTO;
import com.ums.presentation.dto.UpdateUserRequestDTO;
import com.ums.domain.Role;
import com.ums.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserDTOMapper {
    //TODO: What if dto had "Inactive" instead of "INACTIVE"? Or it had different dto value
    @Mapping(target = "state",constant = "INACTIVE")
    @Mapping(target = "role", source = "roleId", qualifiedByName = "ToDomainRole")
    User toDomain(AddUserRequestDTO dto);

    @Mapping(target = "cin", ignore = true)
    @Mapping(target = "state", source = "state", defaultValue = "INACTIVE")
    @Mapping(target = "role", source="roleId",qualifiedByName = "ToDomainRole")
    User toDomain(UpdateUserRequestDTO dto);

    @Named("ToDomainRole")
    default Role mapRoleIdToDomain(Integer roleId){
        if(roleId==null) return null;
        return new Role(roleId, null);
    }

}
