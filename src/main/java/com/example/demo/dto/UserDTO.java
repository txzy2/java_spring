package com.example.demo.dto;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String name,
        Integer Age
) {
}
