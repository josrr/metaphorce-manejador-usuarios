package com.jmarr.authserver.repositories;

import com.jmarr.authserver.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);
}
