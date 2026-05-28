package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class TestController extends BaseController {

    @GetMapping("/test")
    public ResponseEntity test(@RequestParam GetType type) {
        return type == GetType.SUCCESS
                ? ResponseEntity.ok("SUCCESS")
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR");
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> getUser(@Validated @RequestParam String name) {
        return ResponseEntity.ok(new UserDTO(UUID.randomUUID(), name, 25));
    }
}
