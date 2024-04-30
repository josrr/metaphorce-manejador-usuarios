package com.jmarr.users.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmarr.users.controllers.mapper.RoleMapper;
import com.jmarr.users.controllers.response.RoleResponse;
import com.jmarr.users.models.Role;
import com.jmarr.users.repositories.RoleRepository;
import com.jmarr.users.services.RoleServiceI;

import com.jmarr.users.services.ResourceNotFoundException;

@Service
public class RoleService implements RoleServiceI
{
    @Autowired
    private RoleRepository repository;

    @Autowired
    private RoleMapper mapper;

    @Override
    public RoleResponse getByName(String name) throws RuntimeException
    {
        Optional<Role> role = repository.findByName(name);
        if ( !role.isPresent() )
            throw new ResourceNotFoundException(String.format("No existe el rol(%s)", name));
        return mapper.toResponse(role.get());
    }

}
