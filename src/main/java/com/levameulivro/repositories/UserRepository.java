package com.levameulivro.repositories;

import java.util.Optional;

import com.levameulivro.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByName(String name);
    Optional<User> findByEmail(String email);
}
