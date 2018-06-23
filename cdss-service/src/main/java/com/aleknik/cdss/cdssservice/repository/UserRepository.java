package com.aleknik.cdss.cdssservice.repository;

import com.aleknik.cdss.cdssservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("select user from User user join user.roles role where role.name = :role ")
    List<User> findByRole(@Param("role") String role);
}

