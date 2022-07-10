package com.cpswork.backend.repositories;

import com.cpswork.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findByEmail(String email);

    Optional<User> findById(UUID id);
}