package com.example.demo.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginRequest {
    @NotBlank(message = "Электронная почта не может быть пустой")
    @Email(message = "Почта не соответствует правилам")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 8, max = 32, message = "Пароль должен быть не меньше 8 и не больше 32 символов")
    private String password;


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
