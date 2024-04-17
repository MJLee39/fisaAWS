package org.example.estate.exception;

public class NotExistException extends Exception {
    public NotExistException() {}

    public NotExistException(String message) {
        super(message);
    }
}
