package com.jmarr.users.controllers.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jmarr.users.controllers.request.UserRequest;
import com.jmarr.users.controllers.request.UpdateUserRequest;
import com.jmarr.users.controllers.response.UserResponse;
import com.jmarr.users.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "roles", source = "roles")
    UserResponse toResponse(User entity);
    List<UserResponse> toResponseList(List<User> entity);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserRequest response);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "enabled", target = "enabled")
    @Mapping(target = "roles", ignore = true)
    User toEntity(UpdateUserRequest response);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User requestToUser(UserRequest request);

}
