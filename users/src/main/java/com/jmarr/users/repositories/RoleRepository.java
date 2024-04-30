package com.jmarr.users.repositories;

import java.util.Optional;

import com.jmarr.users.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

    @Override
    void delete(Role role);
}
