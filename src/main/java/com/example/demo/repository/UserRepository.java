package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Находит пользователя по external ID
     */
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.bookings WHERE u.extId = :extId")
    Optional<User> findByExtId(@Param("extId") UUID extId);

    /**
     * Находит пользователя по email. Чувствительно к регистру.
     */
    Optional<User> findByEmail(@Param("email") String email);

}

