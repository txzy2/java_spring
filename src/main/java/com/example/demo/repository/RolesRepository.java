package com.example.demo.repository;

import com.example.demo.entity.Roles;
import com.example.demo.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {

    @Query("SELECT r FROM Roles r WHERE r.name = :role")
    Optional<Roles> findByName(@Param("role") RoleName role);
}
