package com.ercan.exceptions;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException() {
        super("Question not found!");
    }

    public QuestionNotFoundException(String message) {
        super(message);
    }
}
