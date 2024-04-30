package com.jmarr.users.services;

import java.util.List;

import com.jmarr.users.controllers.request.UserRequest;
import com.jmarr.users.controllers.request.UpdateUserRequest;
import com.jmarr.users.controllers.response.UserResponse;

public interface UserServiceI {
    public List<UserResponse> getAll();
    public UserResponse getById(Long id);
    public UserResponse save(UserRequest user);
    public UserResponse update(UpdateUserRequest user);
    public boolean deleteById(Long userid);
}
