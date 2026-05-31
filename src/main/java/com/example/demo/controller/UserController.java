package com.example.demo.controller;

import com.example.demo.request.UserRegisterRequest;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponse> getUser(@Validated @RequestParam(name = "ext_id") UUID extId) {
        return ResponseEntity.ok(this.userService.findUserByExtIdOrThrow(extId));
    }

    @PostMapping("/user/register")
    public ResponseEntity<UserResponse> registerUser(@Validated @RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(this.userService.registerUser(request));
    }

}