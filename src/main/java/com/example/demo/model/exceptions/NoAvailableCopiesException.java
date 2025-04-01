package com.example.demo.model.exceptions;

public class NoAvailableCopiesException extends RuntimeException{
    public NoAvailableCopiesException(String message) {
        super(message);
    }
}
