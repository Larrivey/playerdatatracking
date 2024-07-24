package com.playerdatatracking.exceptions;

public class PlayerDataDBException extends Exception {
    // Constructor que acepta un mensaje
    public PlayerDataDBException(String message) {
        super(message);
    }

    // Constructor que acepta un mensaje y una causa
    public PlayerDataDBException(String message, Throwable cause) {
        super(message, cause);
    }
}
