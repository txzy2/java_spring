package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.UserRegisterRequest;
import com.example.demo.response.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Найти пользователя по имени.
     *
     * @param name имя пользователя
     * @return данные пользователя
     * @throws UserNotFoundException если пользователь не найден
     */
    public UserResponse findUserByName(String name) {
        Optional<User> user = this.userRepository.findByUserNamedParam(name);

        if (user.isEmpty()) {
            throw new UserNotFoundException(name, "not found");
        }

        return new UserResponse(user.get());
    }

    /**
     * Зарегистрировать нового пользователя.
     *
     * @param body данные для регистрации (name, email, password, age)
     * @return данные созданного пользователя
     * @throws UserAlreadyExistException если email уже занят
     */
    public UserResponse registerUser(UserRegisterRequest body) {
        if (!this.userRepository.findByUserEmail(body.getEmail()).isEmpty()) {
            throw new UserAlreadyExistException(body.getEmail(), "exist");
        }

        User newUser = new User();

        String hashedPassword = passwordEncoder.encode(body.getPassword());
        newUser.setPassword(hashedPassword);
        newUser.setEmail(body.getEmail());
        newUser.setAge(body.getAge());
        newUser.setName(body.getName());

        User savedUser = userRepository.save(newUser);
        return new UserResponse(savedUser);
    }
}