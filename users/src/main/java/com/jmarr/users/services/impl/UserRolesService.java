package com.jmarr.users.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmarr.users.controllers.mapper.UserRolesMapper;
import com.jmarr.users.controllers.request.UserRolesRequest;
import com.jmarr.users.controllers.response.UserRolesResponse;
import com.jmarr.users.models.UserRoles;
import com.jmarr.users.repositories.UserRolesRepository;
import com.jmarr.users.services.UserRolesServiceI;

import com.jmarr.users.models.UserRolesId;
import com.jmarr.users.services.ResourceNotFoundException;

@Service
public class UserRolesService implements UserRolesServiceI
{
    @Autowired
    private UserRolesRepository repository;

    @Autowired
    private UserRolesMapper mapper;

    @Override
    public List<UserRolesResponse> getAll()
    {
        return mapper.fromEntity(repository.findAll());
    }

    @Override
    public UserRolesResponse getById(Long userId, Integer roleId)
    {
        Optional<UserRoles> userRoles = repository.findById(new UserRolesId(userId, roleId));
        return userRoles.isPresent()
            ? mapper.userRolesToResponse(userRoles.get())
            : null;
    }

    @Override
    public UserRolesResponse getByUserId(Long userId) throws RuntimeException
    {
        Optional<UserRoles> userRoles = repository.findByUserId(userId);
        if (! userRoles.isPresent() )
            throw new ResourceNotFoundException(String.format("El usuario(%d) no tiene roles", userId));
        return mapper.userRolesToResponse(userRoles.get());
    }

    @Override
    public UserRolesResponse save(UserRolesRequest userRolesReq)
    {
        System.out.println("userRolesReq:" + userRolesReq);
        return mapper.userRolesToResponse(repository.save(mapper.requestToUserRoles(userRolesReq)));
    }

    @Override
    public UserRolesResponse update(UserRolesRequest userRolesReq)
    {
        return mapper.userRolesToResponse(repository.save(mapper.requestToUserRoles(userRolesReq)));
    }

    @Override
    public boolean deleteById(Long userId, Integer roleId) throws RuntimeException
    {
        getById(userId, roleId);
        repository.deleteById(new UserRolesId(userId, roleId));
        return true;
    }

}
