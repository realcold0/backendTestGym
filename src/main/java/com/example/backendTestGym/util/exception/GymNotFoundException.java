package com.example.backendTestGym.util.exception;

public class GymNotFoundException extends RuntimeException {
    public GymNotFoundException(String message) {
        super(message);
    }
}
