package com.ums.presentation.dto;

public record UpdateUserRequestDTO(
        String username,
        String passwd,
        String state,
        Integer roleId
) {
}
