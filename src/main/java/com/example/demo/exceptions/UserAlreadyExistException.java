package com.example.demo.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String name, String message) {
        super("User with name '" + name + "' " + message);
    }
}
