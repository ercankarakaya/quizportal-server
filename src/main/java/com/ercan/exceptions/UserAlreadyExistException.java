package com.ercan.exceptions;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException() {
        super("User already present!");
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}

