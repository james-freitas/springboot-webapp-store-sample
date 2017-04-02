package com.codeonblue.service;

public class InvalidPictureException extends RuntimeException {

    public InvalidPictureException(String message) {
        super(message);
    }

    public InvalidPictureException(String message, Throwable cause) { super(message, cause); }

}
