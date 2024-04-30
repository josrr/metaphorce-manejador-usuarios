package com.jmarr.users.controllers.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jmarr.users.controllers.response.RoleResponse;
import com.jmarr.users.models.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "privileges", source = "privileges")
    RoleResponse toResponse(Role entity);
    List<RoleResponse> toResponseList(List<Role> entity);
}
