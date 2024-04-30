package com.jmarr.users.controllers.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.jmarr.users.controllers.response.UserRolesResponse;
import com.jmarr.users.controllers.request.UserRolesRequest;
import com.jmarr.users.models.UserRoles;

@Mapper(componentModel = "spring")
public interface UserRolesMapper
{
    UserRolesResponse userRolesToResponse(UserRoles entity);
    UserRoles requestToUserRoles(UserRolesRequest request);
    List<UserRolesResponse> fromEntity(List<UserRoles> entity);
}
