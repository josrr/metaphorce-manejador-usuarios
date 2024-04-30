package com.jmarr.users.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jmarr.users.models.UserRoles;
import com.jmarr.users.models.UserRolesId;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRoles, UserRolesId>
{
    Optional<UserRoles> findById(UserRolesId id);
    Optional<UserRoles> findByUserId(Long id);
    List<UserRoles> findAll();
}
