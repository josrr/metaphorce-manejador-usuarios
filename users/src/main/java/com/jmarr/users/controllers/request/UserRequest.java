package com.jmarr.users.controllers.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserRequest {
    @NotEmpty(message = "No debe ser vacío")
    @NotNull(message = "No debe ser nulo")
    private String name;

    @NotEmpty(message = "No debe ser vacío")
    @NotNull(message = "No debe ser nulo")
    private String email;

    @NotEmpty(message = "No debe ser vacío")
    @NotNull(message = "No debe ser nulo")
    private String password;
}
