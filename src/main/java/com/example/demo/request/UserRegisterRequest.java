package com.example.demo.request;

import jakarta.validation.constraints.*;

public class UserRegisterRequest {
    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 50, message = "Имя должно быть от 2 до 50 символов")
    private String name;

    @NotBlank(message = "Электронная почта не может быть пустой")
    @Email(message = "Почта не соответствует правилам")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 8, max = 32, message = "Пароль должен быть не меньше 8 и не больше 32 символов")
    private String password;

    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be greater than 0")
    @Max(value = 150, message = "Age must be less than 150")
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
