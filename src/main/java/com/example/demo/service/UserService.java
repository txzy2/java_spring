package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.UserRegisterRequest;
import com.example.demo.response.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RedisService redisService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.redisService = redisService;
    }

    /**
     * Найти пользователя по имени.
     *
     * @param hash уникальный хэш
     * @return данные пользователя
     * @throws UserNotFoundException если пользователь не найден
     */
    public UserResponse findUserByHash(String hash) {
        Optional<UserResponse> cached = this.redisService.get(hash, UserResponse.class);
        if (cached.isPresent()) {
            return cached.get();
        }

        User user = this.userRepository.findByUniqueHash(hash)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        UserResponse response = new UserResponse(user);
        this.redisService.set(hash, response, Duration.ofMinutes(30));

        return response;
    }

    /**
     * Зарегистрировать нового пользователя.
     *
     * @param body данные для регистрации (name, email, password, age)
     * @return данные созданного пользователя
     * @throws UserAlreadyExistException если email уже занят
     */
    public UserResponse registerUser(UserRegisterRequest body) {
        if (this.userRepository.findByUserEmail(body.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(body.getEmail(), "exist");
        }

        User newUser = new User();

        String hashedPassword = passwordEncoder.encode(body.getPassword());
        newUser.setPassword(hashedPassword);
        newUser.setEmail(body.getEmail());
        newUser.setAge(body.getAge());
        newUser.setName(body.getName());
        newUser.setUserHash(passwordEncoder.encode(body.getName() + body.getAge() + body.getEmail()));

        User savedUser = userRepository.save(newUser);
        this.redisService.set(savedUser.getUserHash(), new UserResponse(savedUser), Duration.ofMinutes(30));
        
        return new UserResponse(savedUser);
    }
}