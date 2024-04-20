package com.example.backendTestGym.util.exception;

public class RentalItemAlreadyExistsException extends RuntimeException {
    public RentalItemAlreadyExistsException(String message) {
        super(message);
    }
}
