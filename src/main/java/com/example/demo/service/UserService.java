package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.UserLoginRequest;
import com.example.demo.request.UserRegisterRequest;
import com.example.demo.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RolesService rolesService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;

    public UserService(UserRepository userRepository, RolesService rolesService, PasswordEncoder passwordEncoder,
                       RedisService redisService, JwtService jwtService) {
        this.userRepository = userRepository;
        this.rolesService = rolesService;
        this.passwordEncoder = passwordEncoder;
        this.redisService = redisService;
        this.jwtService = jwtService;
    }

    /**
     * Найти пользователя по имени.
     *
     * @param extId уникальный идентификатор пользователя
     * @return данные пользователя
     * @throws UserNotFoundException если пользователь не найден
     */
    public UserResponse findUserByExtIdOrThrow(UUID extId) {
        Optional<UserResponse> cached = redisService.get(extId.toString(), UserResponse.class);
        if (cached.isPresent()) {
            return cached.get();
        }

        User user = userRepository.findByExtId(extId)
                .orElseThrow(() -> {
                    logger.warn("USER {} not found", extId);
                    return new UserNotFoundException("User not found");
                });

        UserResponse response = new UserResponse(user);
        redisService.set(extId.toString(), response, Duration.ofMinutes(30));

        return response;
    }

    /**
     * Зарегистрировать нового пользователя.
     *
     * @param body данные для регистрации (name, email, password, age)
     * @return данные созданного пользователя
     * @throws UserAlreadyExistException если email уже занят
     */
    public UUID registerUser(UserRegisterRequest body) {
        userRepository.findByEmail(body.getEmail())
                .ifPresent(user -> {
                    throw new UserAlreadyExistException("email", body.getEmail());
                });

        User savedUser = userRepository.save(User.create(
                body.getEmail(),
                body.getName(),
                body.getAge(),
                passwordEncoder.encode(body.getPassword()),
                passwordEncoder.encode(body.getName() + body.getAge() + body.getEmail()),
                rolesService.findRoleByNameOrThrow(body.getRole()),
                UUID.randomUUID()
        ));

        return savedUser.getExtId();
    }

    /**
     * Вход пользователя
     *
     * @param request данные входа пользователя (email, password)
     * @return JWT токен
     * @throws UserNotFoundException пользователь не найден
     */
    public String login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        redisService.set(user.getExtId().toString(), new UserResponse(user), Duration.ofMinutes(30));
        return jwtService.generate(user);
    }
}