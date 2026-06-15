package com.example.demo.controller;

import com.example.demo.request.UserLoginRequest;
import com.example.demo.request.UserRegisterRequest;
import com.example.demo.response.BaseApiResponse;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<BaseApiResponse<UserResponse>> getUser(@Validated @RequestParam(name = "ext_id") UUID extId) {
        return ResponseEntity.ok(BaseApiResponse.ok(userService.findUserByExtIdOrThrow(extId)));
    }

    @PostMapping("/register")
    public ResponseEntity<BaseApiResponse<UUID>> registerUser(@Validated @RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(BaseApiResponse.ok(userService.registerUser(request)));
    }

    @PostMapping("/login")
    public ResponseEntity<BaseApiResponse<String>> login(@RequestBody UserLoginRequest request) {
        return ResponseEntity.ok(BaseApiResponse.ok(userService.login(request)));
    }

}