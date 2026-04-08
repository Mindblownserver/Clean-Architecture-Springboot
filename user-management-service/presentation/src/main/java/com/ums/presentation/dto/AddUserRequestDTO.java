package com.ums.presentation.dto;

import jakarta.validation.constraints.*;

public record AddUserRequestDTO(
        @NotBlank(message = "cin can't be empty")
        @Size(message = "cin must have 8 characters", min=8, max=8)
        String cin,

        @NotBlank(message = "username can't be empty")
        String username,

        @NotBlank(message = "password can't be empty")
        String passwd,

        @Min(0) @Max(10)
        Integer roleId
) {
}
