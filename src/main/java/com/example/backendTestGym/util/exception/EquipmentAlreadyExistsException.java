package com.example.backendTestGym.util.exception;

public class EquipmentAlreadyExistsException extends RuntimeException {
    public EquipmentAlreadyExistsException(String message) {
        super(message);
    }
}
