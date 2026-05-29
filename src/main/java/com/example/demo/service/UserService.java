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

    public UserResponse findUserByName(String name) {
        Optional<User> user = this.userRepository.findByUserNamedParam(name);

        if (user.isEmpty()) {
            throw new UserNotFoundException(name, "not found");
        }

        return new UserResponse(user.get());
    }

    public UserResponse registerUser(UserRegisterRequest body) {
        if (!this.userRepository.findByUserEmail(body.getEmail()).isEmpty()) {
            throw new UserAlreadyExistException(body.getEmail(), "exist");
        }

        User newUser = new User();
        String hashedPassword = passwordEncoder.encode(body.getPassword());
        newUser.setPassword(hashedPassword);
        newUser.setAge(body.getAge());
        newUser.setName(body.getName());

        User savedUser = userRepository.save(newUser);

        return new UserResponse(savedUser);
    }
}
