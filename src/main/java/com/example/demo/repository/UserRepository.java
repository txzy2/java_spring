package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Находит пользователя по его имени.
     *
     * @param name имя пользователя для поиска (не может быть null)
     * @return {@link Optional}, содержащий найденного пользователя,
     * или {@link Optional#empty()} если пользователь не найден
     * @throws IllegalArgumentException если name равен null
     * @apiNote Использует JPQL запрос для поиска по точному совпадению имени
     * @see User#getName()
     */
    @Query("SELECT u FROM User u WHERE u.name = :userName")
    Optional<User> findByUserNamedParam(@Param("userName") String name);

    /**
     * Находит пользователя по его электронной почте.
     *
     * <p>Использует точное совпадение email (чувствительно к регистру).</p>
     *
     * @param email электронная почта пользователя (не может быть null)
     * @return {@link Optional}, содержащий найденного пользователя,
     * или {@link Optional#empty()} если пользователь с таким email не найден
     * @throws IllegalArgumentException если email равен null
     * @apiNote Использует JPQL запрос для поиска по точному совпадению email
     * @see User#getEmail()
     */
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByUserEmail(@Param("email") String email);

}

