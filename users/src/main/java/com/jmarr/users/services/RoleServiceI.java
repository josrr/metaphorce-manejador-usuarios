package com.jmarr.users.services;

import com.jmarr.users.controllers.response.RoleResponse;

public interface RoleServiceI {
    public RoleResponse getByName(String name);
}
