package com.jmarr.users.controllers.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateUserRequest extends UserRequest {
    @Min(1)
    private Long id;

    @NotEmpty(message = "No debe ser vacío")
    @NotNull(message = "No debe ser nulo")
    private boolean enabled;
}
