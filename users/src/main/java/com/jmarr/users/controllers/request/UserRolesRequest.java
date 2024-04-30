package com.jmarr.users.controllers.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRolesRequest {
    @Min(value = 1, message = "Debe ser mayor o igual a 1")
    @NotNull
    private Long userId;

    @Min(value = 1, message = "Debe ser mayor o igual a 1")
    @NotNull
    private Integer roleId;
}
