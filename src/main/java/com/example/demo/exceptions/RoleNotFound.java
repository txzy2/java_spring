package com.example.demo.exceptions;

public class RoleNotFound extends RuntimeException {
    public RoleNotFound(String message, String string) {
        super(message + string);
    }
}
