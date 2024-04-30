package com.jmarr.users.repositories;

import java.util.Optional;
import com.jmarr.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Override
    void delete(User user);
}
