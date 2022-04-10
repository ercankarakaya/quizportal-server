package com.ercan.exceptions;

public class QuizNotFoundException extends RuntimeException {

    public QuizNotFoundException() {
        super("Quiz not found!");
    }

    public QuizNotFoundException(String message) {
        super(message);
    }
}
