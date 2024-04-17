package com.example.zip_grpc.exception;

public class NotExistException extends Exception {
    public NotExistException() {}

    public NotExistException(String message) {
        super(message);
    }
}
