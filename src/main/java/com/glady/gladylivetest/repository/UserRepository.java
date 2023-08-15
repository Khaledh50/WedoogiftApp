package com.glady.gladylivetest.repository;

import com.glady.gladylivetest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Add custom queries if needed


    Optional<User> getUserByPseudo(String pseudo);
}
