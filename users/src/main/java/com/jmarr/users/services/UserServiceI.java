package com.jmarr.users.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jmarr.users.controllers.request.UserRequest;
import com.jmarr.users.controllers.request.UpdateUserRequest;
import com.jmarr.users.controllers.response.UserResponse;

public interface UserServiceI {
    public Page<UserResponse> getAll(Pageable pageable);
    public UserResponse getById(Long id);
    public UserResponse save(UserRequest user);
    public UserResponse update(UpdateUserRequest user);
    public boolean deleteById(Long userid);
}
