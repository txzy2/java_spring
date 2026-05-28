package com.example.demo.request;

import jakarta.validation.constraints.*;

public class UserTestRequest {
    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 50, message = "Имя должно быть от 2 до 50 символов")
    private String name;

    public String getName() {
        return name;
    }
}
