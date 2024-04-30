package com.jmarr.users.services;

import java.util.List;

import com.jmarr.users.controllers.request.UserRolesRequest;
import com.jmarr.users.controllers.response.UserRolesResponse;

public interface UserRolesServiceI {
    public List<UserRolesResponse> getAll();
    public UserRolesResponse getById(Long usrId, Integer roleId);
    public UserRolesResponse getByUserId(Long usrId);
    public UserRolesResponse save(UserRolesRequest usrRoles);
    public UserRolesResponse update(UserRolesRequest usrRoles);
    public boolean deleteById(Long usrId, Integer filterId);
}
