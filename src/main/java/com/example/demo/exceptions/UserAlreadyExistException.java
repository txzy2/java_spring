package com.example.demo.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String paramName, String param) {
        super("Пользователь с '" + paramName + ": " + param + "' уже существует");
    }
}
