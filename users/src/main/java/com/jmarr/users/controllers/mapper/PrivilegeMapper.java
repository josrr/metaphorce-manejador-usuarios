package com.jmarr.users.controllers.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jmarr.users.controllers.response.PrivilegeResponse;
import com.jmarr.users.models.Privilege;

@Mapper(componentModel = "spring")
public interface PrivilegeMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    PrivilegeResponse toResponse(Privilege entity);
    List<PrivilegeResponse> toResponseList(List<Privilege> entity);
}
