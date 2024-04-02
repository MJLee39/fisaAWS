package com.example.zip1.exception;

public class NotExistException extends Exception {
    public NotExistException() {}

    public NotExistException(String message) {
        super(message);
    }
}
